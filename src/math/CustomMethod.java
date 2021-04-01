package math;

public class CustomMethod {
    public static boolean isPrimal(int number) {
        for (int i = 2; i < number/2; i++) {
            if ((i % number) == 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPalindrome(int number) {
        char[] c = Integer.toString(number).toCharArray();
        int substract = 0;
        if (c.length%2 == 1)substract = 1;
        for (int i = 0; i < (c.length-substract)/2; i++)
            if (c[i] != c[c.length-(i+1)]) return false;
        return true;
    }
    /**
     * Estimate the area under a portion of a curve define by a f Function with the
     * Rieman summation square method
     * @param f function to evaluate, assuming the it is continuous on the portion
     * @param a the lower bound
     * @param b the upper bound, shall be greater than a
     * @param n the number of square, shall be greater or equals than 1
     * @return the area under the curve. If one ore more value are invalid return NaN
     */
    public static double areaS(IFunction f,double a, double b, int n) {
        if (b <= a || n <= 0) return Double.NaN;
        double sum = 0;
        for (double i = a; i < b; i += (b-a)/n) {
            sum += (b-a)/n * f.evaluate(i);
        }
        return sum;
    }
    /**
     * Estimate the area under a portion of a curve define by a f Function with the
     * Rieman summation trapeze method
     * @param f function to evaluate, assuming the it is continuous on the portion
     * @param a the lower bound
     * @param b the upper bound, shall be greater than a
     * @param n the number of square, shall be greater or equals than 1
     * @return the area under the curve. If one ore more value are invalid return NaN
     */
    public static double areaT(IFunction f,double a, double b, int n) {
        if (b <= a || n <= 0) return Double.NaN;
        double sum = 0;
        for (double i = a; i < b; i += (b-a)/n) {
            sum += (b-a)/n * f.evaluate(i);
            sum += ((b-a)/n * (f.evaluate(i+(b-a)/n)-f.evaluate(i)))/2;
        }
        return sum;
    }
}
