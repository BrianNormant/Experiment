import math.Matrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class HammingCode {
    private static int nbErrors = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Reading data using readLine
        System.out.println("Nom?");
        String name = reader.readLine();
        System.out.println(utility.ArrayUtility.doubleArrayToReturnString((
                utility.WrapperConverter.intArrayToInteger(encode(name))
        )));

        System.out.println("Coller le binaire ici (pur texte)");
        String s = reader.readLine();
        final String sVerif = s;
        s = s.replace(" ", "");
        s = s.replace("\n", "");

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
        if (test.length * test[0].length != sVerif.length()) throw new IllegalArgumentException("Il faut fournir un nombre entier de paquet de 7 bit");
        System.out.println(Arrays.deepToString(test));
        System.out.println("Le texte est :\n" + decode(test));
        System.out.println("Il y avait "+ nbErrors +" erreur"+((nbErrors>1)?"s":"" )+" dans le texte");
    }

    public static final Matrix<Integer> MAT_ENCODING = Matrix.createDefineIntegerMatrix(4,7,
            new Integer[][] {
                    {1,1,1,0,0,0,0}, //0
                    {1,0,0,1,1,0,0}, //1
                    {0,1,0,1,0,1,0}, //2
                    {1,1,0,1,0,0,1}  //3
            }
            );
    public static final Matrix<Integer> MAT_CONTROL = Matrix.createDefineIntegerMatrix(3,7,
            new Integer[][] {
                    {0,0,0,1,1,1,1},
                    {0,1,1,0,0,1,1},
                    {1,0,1,0,1,0,1}
            }
    );
    public static final Matrix<Integer> MAT_DECODING = Matrix.createDefineIntegerMatrix(7,4,
            new Integer[][] {
                    {0,0,0,0}, //0
                    {0,0,0,0}, //1
                    {1,0,0,0},  //2
                    {0,0,0,0},  //3
                    {0,1,0,0},  //4
                    {0,0,1,0},  //5
                    {0,0,0,1}   //6
            }
    );

    private static String decode(int[][] test) {
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
                    if (!errorVec.getElement(x, y).equals(0) && !errorVec.getElement(x, y).equals(1)) {
                        errorVec.setElement(x, y, (errorVec.getElement(x, y) % 2 == 0) ? 0 : 1);
                    }
                    vecErrString += "" + errorVec.getElement(x,y);
                }
            int errorIndex = Integer.parseInt(vecErrString,2);
            if (errorIndex != 0) {
                errorIndex --; //the index need to go to 0,1,2 from 1,2,3
                //Correct the error bit "flipping" the int
                nbErrors++;
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
        return utility.ASCII.binaryToString(binaryString);
    }
    private static int[][] encode(String message) {
        Integer[][] rawMessage = utility.WrapperConverter.intArrayToInteger(utility.ASCII.stringToBinary(message));
        var encodedMessage = new int[rawMessage.length][7];
        for (int i = 0; i < encodedMessage.length; i++) {
            var lineMessage = Matrix.createDefineIntegerMatrix(1,4,new Integer[][]{rawMessage[i]});
            var lineEncodedMessage = lineMessage.mul(MAT_ENCODING);
            encodedMessage[i] = utility.WrapperConverter.integerArrayToInt(lineEncodedMessage.getMatrixAsArray()[0]);
        }
        return encodedMessage;
    }
}
