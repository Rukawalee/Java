/**
 * 快排实现
 */
public class QuickSort {
    /**
     * 排序实现
     * @param data 待排序数据
     * @return 返回排序后数据
     */
    public int[] sort(int[] data, int left, int right){
        int[] sortData = data.clone();
        int l = left;
        int r = right;
        // 定义基准值
        int p = sortData[l];
        while(l < r){
            // 如果右边的数据比基准大，则右指针减小
            while(l < r && p < sortData[r]){
                r--;
            }
            // 将右指针的数据移动到左指针位置
            if(l < r){
                sortData[l] = sortData[r];
                l++;
            }
            // 如果左边的数据比基准小，则左指针增大
            while(l < r && p > sortData[l]){
                l++;
            }
            // 将左指针的数据移动到右指针位置
            if(l < r){
                sortData[r] = sortData[l];
                r--;
            }
        }
        // 将基准移动到指定位置
        sortData[l] = p;
        // 将左边部分再次排序
        if(l - 1 > left){
            sortData = sort(sortData, left, l - 1);
        }
        // 将右边部分再次排序
        if(r + 1 < right){
            sortData = sort(sortData, r + 1, right);
        }
        return sortData;
    }

    /**
     * 显示数据
     * @param data 待显示数据
     */
    public void show(int[] data){
        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{3, 4, 2, 1, 7, 6, 5};
        QuickSort quickSort = new QuickSort();
        System.out.println("排序前数据");
        quickSort.show(data);
        int[] sort = quickSort.sort(data, 0, data.length - 1);
        System.out.println();
        System.out.println("排序后数据");
        quickSort.show(sort);
    }
}
