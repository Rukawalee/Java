/**
 * 选择排序实现
 */
public class SelectionSort {
    /**
     * 选择排序
     * @param data 数据
     * @return 返回排序后的数据
     */
    public int[] sort(int[] data){
        int[] sortData = data.clone();
        int tmp = 0;
        for (int i = sortData.length - 1; i > 0; i--) {
            int select = i;
            for (int j = 0; j < i; j++) {
                if(sortData[j] > sortData[select]){
                    select = j;
                }
            }
            if(i != select){
                tmp = sortData[select];
                sortData[select] = sortData[i];
                sortData[i] = tmp;
            }
        }
        return sortData;
    }

    /**
     * 展示数据
     * @param data 数据
     */
    public void show(int[] data){
        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 8, 7, 5, 6, 2, 4, 3};
        SelectionSort selectionSort = new SelectionSort();
        System.out.println("排序前数据");
        selectionSort.show(data);
        int[] sort = selectionSort.sort(data);
        System.out.println();
        System.out.println("排序后数据");
        selectionSort.show(sort);
    }
}
