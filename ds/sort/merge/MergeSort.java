package sort.merge;

/**
 * 归并排序实现
 **/
public class MergeSort {
    /**
     * 排序实现
     * @param data 待排序数据
     * @return 排序后数据
     */
    public int[] sort(int[] data){
        int[] sortData = data.clone();
        int[] temp = new int[sortData.length];
        mergeSort(sortData, 0, sortData.length - 1, temp);
        return sortData;
    }

    /**
     * 归并排序
     * @param arr 待排序数据
     * @param left 左边指针
     * @param right 右边指针
     * @param temp 临时数据
     */
    public void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并实现
     * @param arr 待排序数据
     * @param left 左边指针
     * @param mid 中间指针
     * @param right 右边指针
     * @param temp 临时数据
     */
    public void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;

        // 将数组有序加入临时数组
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                i++;
            } else {
                temp[t] = arr[j];
                j++;
            }
            t++;
        }

        // 将剩下的数据加入临时数组
        while(i <= mid){
            temp[t] = arr[i];
            i++;
            t++;
        }
        while(j <= right){
            temp[t] = arr[j];
            j++;
            t++;
        }

        // 将临时数组中的数据拷回
        t = 0;
        int tepLeft = left;
        while(tepLeft <= right){
            arr[tepLeft] = temp[t];
            t++;
            tepLeft++;
        }
    }

    /**
     * 显示数据
     * @param data
     */
    public void show(int[] data){
        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 8, 3, 7, 5, 4, 2, 6};
        MergeSort mergeSort = new MergeSort();
        System.out.println("排序前数据");
        mergeSort.show(data);
        int[] sortData = mergeSort.sort(data);
        System.out.println("\r\n排序后数据");
        mergeSort.show(sortData);
    }
}
