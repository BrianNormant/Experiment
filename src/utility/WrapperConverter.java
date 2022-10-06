package utility;

public class WrapperConverter {
    public static Integer[] intArrayToInteger(int[] ints) {
        Integer[] result = new Integer[ints.length];
        for (int i = 0; i < ints.length; i++) result[i] = ints[i];
        return result;
    }
    public static Integer[][] intArrayToInteger(int[][] ints) {
        Integer[][] result = new Integer[ints.length][ints[0].length];
        for (int i = 0; i < result.length; i++) result[i] = intArrayToInteger(ints[i]);
        return result;
    }
    public static Integer[][][] intArrayToInteger(int[][][] ints) {
        Integer[][][] result = new Integer[ints.length][ints[0].length][ints[0][0].length];
        for (int i = 0; i < result.length; i++) result[i] = intArrayToInteger(ints[i]);
        return result;
    }

    public static int[] integerArrayToInt(Integer[] integers) {
        int[] result = new int[integers.length];
        for (int i = 0; i < result.length; i++) result[i] = integers[i];
        return result;
    }
    public static int[][] integerArrayToInt(Integer[][] integers) {
        int[][] result = new int[integers.length][integers[0].length];
        for (int i = 0; i < result.length; i++) result[i] = integerArrayToInt(integers[i]);
        return result;
    }
}
