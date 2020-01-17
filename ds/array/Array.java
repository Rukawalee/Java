/**
 * 数组
 */
public class Array {
    public static void main(String[] args) {
        // 布尔数组初始化
        boolean[] bs = new boolean[5];          // 初始化5个空间，填充默认值
        boolean[] bs1 = new boolean[]{};        // 初始化一个空数组
        boolean bs2[] = {};                     // 初始化一个空数组
        Boolean booleans[] = {};                // 初始化一个空数组
        System.out.println("booleans' length = " + booleans.length);

        // 整型数组
        int[] is = new int[5];
        int[] is1 = new int[]{};
        int[] is2 = {};
        Integer[] integers = {};
        System.out.println("is' length = " + is.length);

        // 二维数组
        double[][] ds = new double[2][];        // 初始化两行0列数组
        double[][] ds1 = new double[2][];
        ds1[1] = new double[1];                 // 初始化二维数组第二行
        double[][] ds2 = new double[][]{};      // 初始化空数组
        double[] ds3[] = {};                    // 初始化空数组
        double ds4[][] = {{}, {}};              // 初始化两行0列数组
    }
}

