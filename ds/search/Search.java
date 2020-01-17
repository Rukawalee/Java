import java.util.Arrays;

/**
 * 查找实现
 */
public class Search {
    public static void main(String[] args) {
        int[] group = new int[]{10, 7, 9, 8, 5, 1, 2};
        System.out.println(search(group, 2));
        System.out.println(binarySearch(group, 2));
    }

    /**
     * 简单顺序查找
     * @param group 数据组
     * @param target 目标数据
     * @return 返回目标数据索引
     */
    public static int search(int[] group, int target){
        if(group == null || group.length == 0){
            return -1;
        }
        for (int i = 0; i < group.length; i++) {
            if(group[i] == target){
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     * @param group 数据组
     * @param target 目标数据
     * @return 返回目标数据索引
     */
    public static int binarySearch(int[] group, int target){
        if(group == null || group.length == 0){
            return -1;
        }
        int[] groupClone = group.clone();
        // 升序排序
        Arrays.sort(groupClone);
        int left = 0;
        int mid = 0;
        int right = groupClone.length - 1;
        while(left <= right){
            mid = (left + right) / 2;
            if(groupClone[mid] == target){
                return mid;
            } else if(groupClone[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
