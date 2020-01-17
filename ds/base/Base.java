/**
 * 基本数据类型
 */
public class Base {
    public static void main(String[] args) {
        // 布尔型：默认：false，范围：true，false
        boolean bool = false;
        System.out.println("boolean = " + bool);

        // 字符型：默认：'\u0000'，范围：-2^(16-1)~2^(16-1)-1，16bits
        // 包装类：Character
        char c = '\u0000';
        Character character = 1;    // 装箱
        c = character;              // 拆箱
        System.out.println("char min = " + Character.MIN_VALUE + ", max = " + Character.MAX_VALUE + ", bits = " + Character.SIZE);

        // 数值型，默认：整型为int，浮点型为double

        // 字节型：默认：0，范围：-2^(8-1)~2^(8-1)-1，8bits
        // 包装类：Byte
        byte b = 0;
        Byte bt = 1;                // 装箱
        b = bt;                     // 拆箱
        System.out.println("byte min = " + Byte.MIN_VALUE + ", max = " + Byte.MAX_VALUE + ", bits = " + Byte.SIZE);

        // 短整型：默认：0，范围：-2^(16-1)~2^(16-1)-1，16bits
        // 包装类：Short
        short s = 0;
        Short st = 1;               // 装箱
        s = st;                     // 拆箱
        System.out.println("short min = " + Short.MIN_VALUE + ", max = " + Short.MAX_VALUE + ", bits = " + Short.SIZE);

        // 整数型：默认：0，范围：-2^(32-1)~2^(32-1)-1，32bits
        // 包装类：Integer
        int i = 0;
        Integer integer = 1;        // 装箱
        i = integer;                // 拆箱
        System.out.println("int min = " + Integer.MIN_VALUE + ", max = " + Integer.MAX_VALUE + ", bits = " + Integer.SIZE);

        // 长整型：默认：0，范围：-2^(64-1)~2^(64-1)-1，64bits
        // 包装类：Long
        long l = 0L;
        Long lg = 1L;
        l = lg;
        System.out.println("long min = " + Long.MIN_VALUE + ", max = " + Long.MAX_VALUE + ", bits = " + Long.SIZE);

        // 单精度浮点型：默认：0.0F，32bits
        // 包装类：Float
        float f = 0.0F;
        Float ft = 1F;              // 装箱
        f = ft;                     // 拆箱
        System.out.println("float min = " + Float.MIN_VALUE + ", max = " + Float.MAX_VALUE + ", bits = " + Float.SIZE);

        // 双精度浮点型：默认：0.0，64bits
        // 包装类：Double
        double d = 0.0;
        Double db = 1.0;            // 装箱
        d = db;                     // 拆箱
        System.out.println("double min = " + Double.MIN_VALUE + ", max = " + Double.MAX_VALUE + ", bits = " + Double.SIZE);

        // 引用型：默认：null
        String str = null;
        System.out.println("String = " + str);
    }
}
