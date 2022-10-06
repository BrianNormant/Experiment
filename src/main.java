import math.Matrix;

import java.util.Objects;

public class main {
    public static void main(String[] args) {
        String s = "10011001010101110011011111111100110001011001010100000000110011010000111100110111111100011110100101000111101010100001111100001101010100000000110011010011001100110010010101010100000000110011010101011100110110100100011111001100110011011100000010110001100111001101010101110011011010010001111100110011001100011001000111111010010001111010010111001100100101000111110000110101010000000011001100100101000111110000110001111100110001010100000000110011001001010001111111000000011111001100000111101010101100110110100111001101111111000111101010101100110100110011001100011001110011000101101100110110100111001100011001000111101010101100110010010101010100010110";
        //        "0010100" +
        //            "1110001" +
        //            "0010010"+
        //            "1111110";
        int[][] test = new int[s.length()/7][7];
        {
            int i = 0;
            while (s.length() >= 7) {
                //System.out.println(s.substring(0,7));
                String ss = s.substring(0,7);
                for (int j = 0; j < ss.length(); j++) {
                    char c = ss.charAt(j);
                    test[i][j] = (c == '0')?0:1;
                }
                s = s.substring(7);
                i++;
            }
        }

        for (int[] line : test) {
            for (int bit : line) {
                System.out.print(bit + "");
            }
            System.out.println();
        }

        int[][] rawMessage = new int[test.length][4];
        for (int i = 0; i < test.length; i++) {
            // Convert from int[7][1] to Matrix<Integer>[7,1]
            Integer[][] columnMat = new Integer[1][7]; // {{x,x,x,x,x,x,x}}
            for (int j = 0; j < test[i].length/* will always be 7*/; j++) columnMat[0][j] = test[i][j];
            Matrix<Integer> sevenBits = Matrix.createDefineIntegerMatrix(1,7, columnMat);
            // Finding and correcting errors
            Matrix<Integer> errorVec = sevenBits.mul(HammingCode.MAT_CONTROL.transpose());
            String vecErrString = "";
            for (int x = 0; x < errorVec.getXSize(); x++)
                for (int y = 0; y < errorVec.getYSize(); y++) {
                    if (errorVec.getElement(x, y) == null) {
                        errorVec.setElement(x, y, -1);
                        continue;
                    }
                    if (!errorVec.getElement(x, y).equals(0) && !errorVec.getElement(x, y).equals(1)) {
                        errorVec.setElement(x, y, (errorVec.getElement(x, y) % 2 == 0) ? 0 : 1);
                    }
                    vecErrString += "" + errorVec.getElement(x,y);
                }
            int errorIndex = Integer.parseInt(vecErrString,2);
            if (errorIndex != 0) {
                errorIndex --; //the index need to go to 0,1,2 from 1,2,3
                //Correct the error bit "flipping" the int
                sevenBits.setElement(0,errorIndex, (Objects.equals(sevenBits.getElement(0, errorIndex), 0))?1:0);
            }
            //Convert from Matrix<Integer>[7,1] to Matrix<Integer>[4,1] then to int[4][1]
            Matrix<Integer> fourBits = sevenBits.mul(HammingCode.MAT_DECODING);
            for (int x = 0; x < fourBits.getXSize(); x++)
                for (int y = 0; y < fourBits.getYSize(); y++) {
                    if (!fourBits.getElement(x, y).equals(0) && !fourBits.getElement(x, y).equals(1)) {
                        fourBits.setElement(x, y, (fourBits.getElement(x, y) % 2 == 0) ? 0 : 1);
                    }
                }
            for (int j = 0; j < fourBits.getYSize()/*Will always be 4*/; j++) rawMessage[i][j] = fourBits.getElement(0,j);
        }
        String binaryString = "";
        for (int i = 0; i < rawMessage.length; i += 2) {
            String byteString = "";
            for (int j = 0; j < rawMessage[i].length/*Will always be 4*/; j++) byteString += rawMessage[i][j];
            for (int j = 0; j < rawMessage[i].length; j++) byteString += rawMessage[i+1][j];
            binaryString += byteString;
        }

        String result = "Error";
        System.out.println("Raw Data:");
        for (int[] line: rawMessage) for (int ints: line) {
            System.out.print(ints);
        }
        System.out.println("\nEnd of RawData");
        result = utility.ASCII.binaryToString(binaryString);
        System.out.println("Le message est :\n"+result);
    }
}
