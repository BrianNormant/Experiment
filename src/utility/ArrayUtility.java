package utility;

public class ArrayUtility {
    public static String arrayToLineString(Object[] array) {
        StringBuilder s = new StringBuilder();
        for (Object o : array) {
            s.append(o.toString());
        }
        return s.toString();
    }
    public static String doubleArrayToReturnString(Object[][] array) {
        StringBuilder s = new StringBuilder();
        for (Object[] os : array) {
            s.append(arrayToLineString(os));
            s.append("\n");
        }
        return s.toString();
    }
}
