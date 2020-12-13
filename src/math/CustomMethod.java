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

}
