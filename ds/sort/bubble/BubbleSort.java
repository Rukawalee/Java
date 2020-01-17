/**
 * 冒泡排序实现
 */
public class BubbleSort {
    /**
     * 排序方法
     * @param data 数据
     * @return 返回排序后数据
     */
    public int[] sort(int[] data){
        int[] sortData = data.clone();
        int tmp = 0;
        // 优化标志，如果出现有一次标志没有改变，则说明数据有序
        boolean flag = false;
        // i：定义循环次数
        for (int i = data.length - 1; i > 0; i--) {
            flag = false;
            // j：比较数据的索引
            for (int j = 0; j < i; j++) {
                if(sortData[j] > sortData[j + 1]){
                    // 交换数据
                    tmp = sortData[j];
                    sortData[j] = sortData[j + 1];
                    sortData[j + 1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
        return sortData;
    }

    /**
     * 显示数据
     * @param data 数据
     */
    public void show(int[] data){
        for (int t : data) {
            System.out.print(t + " ");
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] data = new int[]{2, 7, 5, 6, 4, 1, 3};
        int[] sortData = bubbleSort.sort(data);
        bubbleSort.show(sortData);
    }
}
