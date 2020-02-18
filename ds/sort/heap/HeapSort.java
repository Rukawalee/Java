package sort.heap;

public class HeapSort {
    public int[] sort(int[] data) {
        int[] sortData = data.clone();
        // 调整大顶堆
        for (int i = sortData.length / 2 - 1; i >= 0; i--) {
            adjustHeap(sortData, i, sortData.length);
        }
        // 开始排序
        for (int i = sortData.length - 1; i >= 0; i--) {
            int tmp = sortData[i];
            sortData[i] = sortData[0];
            sortData[0] = tmp;
            adjustHeap(sortData, 0, i);
        }
        return sortData;
    }

    public void adjustHeap(int[] data, int offset, int length) {
        int tmp = data[offset];
        for (int i = offset * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && data[i] < data[i + 1]) {
                i = i + 1;
            }
            if (data[i] > tmp) {
                data[offset] = data[i];
                offset = i;
            } else {
                // 后面的数据都比当前堆顶元素小
                break;
            }
        }
        data[offset] = tmp;
    }

    /**
     * 显示数据
     *
     * @param data 待显示数据
     */
    public void show(int[] data) {
        for (int datum : data) {
            System.out.print(datum + " ");
        }
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] data = new int[]{7, 8, 4, 3, 6, 2, 1, 5};
        System.out.println("排序前数据");
        heapSort.show(data);
        System.out.println("\r\n排序后数据");
        int[] sort = heapSort.sort(data);
        heapSort.show(sort);
    }
}
