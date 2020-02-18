package sort.radix;

public class RadixSort {
    /**
     * 排序实现
     * @param data 待排序数据
     * @return 返回排序后数据
     */
    public int[] sort(int[] data){
        int[] sortData = data.clone();
        // 定义数据桶
        int[][] bucket = new int[10][sortData.length];
        // 定义数据桶里面有多少数据
        int[] bucketCount = new int[10];
        // 查找最大数据长度
        int max = sortData[0];
        for (int i = 1; i < sortData.length; i++) {
            if(max < sortData[i]){
                max = sortData[i];
            }
        }
        int maxLen = (max + "").length();
        // 将数据分桶
        for(int i = 0, array = 1; i < maxLen; i++, array *= 10){
            // 将数据分桶
            for (int j = 0; j < sortData.length; j++) {
                int bucketIndex = sortData[j] / array % 10;
                bucket[bucketIndex][bucketCount[bucketIndex]] = sortData[j];
                bucketCount[bucketIndex]++;
            }
            // 将桶中数据放回
            int tmpIndex = 0;
            for (int j = 0; j < bucketCount.length; j++) {
                if(bucketCount[j] != 0){
                    for (int k = 0; k < bucketCount[j]; k++) {
                        sortData[tmpIndex] = bucket[j][k];
                        tmpIndex++;
                    }
                    bucketCount[j] = 0;
                }
            }
        }
        return sortData;
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
        RadixSort radixSort = new RadixSort();
        int[] data = new int[]{10, 3, 25, 78, 66};
        System.out.println("排序前数据");
        radixSort.show(data);
        int[] sort = radixSort.sort(data);
        System.out.println("\r\n排序后数据");
        radixSort.show(sort);
    }
}
