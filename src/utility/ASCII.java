package utility;

public class ASCII {
    public static String binaryToString(String binary) {
        if (binary.length()%8 != 0) throw new IllegalArgumentException("The binary must be a number of packet of 8");
        StringBuilder result = new StringBuilder();
        while (binary.length() >= 8) {
            String s = binary.substring(0,8);
            result.append((char) Integer.parseInt(s, 2));
            binary = binary.substring(8);
        }
        return result.toString();
    }
    public static int[][] stringToBinary(String text) {
        char[] chars = new char[text.length()];
        for (int i = 0; i < chars.length; i++) chars[i] = text.charAt(i);

        int[][] result = new int[chars.length*2][4];
        for (int i = 0; i < chars.length; i++) {
            String binaryString = Integer.toBinaryString(chars[i]);
            for (int j = 0; j < binaryString.length(); j++) {
                if (j<4) {
                    result[2*i][j] = binaryString.charAt(j);
                } else {
                    result[2*i+1][j-4] = binaryString.charAt(j);
                }
            }
        }
        return result;
    }
}
