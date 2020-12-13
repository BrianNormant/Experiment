import java.util.ArrayList;
import java.util.Random;

public class CustomMethod {
    public static final int[] fibonaci;
    public static final String consonnes = "bcdfghjklmnpqrstvwxz";
    public static final String voyelles = "aeiouy";
    public static final String alphabet = consonnes + voyelles;
    static final char[] alphabetChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    static {
        fibonaci = new int[47];// max lengh before i > Integer.MAXSIZE
        fibonaci[0] = 0;
        fibonaci[1] = 1;
        for (int i = 2; i < fibonaci.length; i++) fibonaci[i] = fibonaci[i - 1] + fibonaci[i - 2];
    }

    /**
     * Trier une liste en ordre croissant
     *
     * @param before une liste int
     * @return cette liste en ordre croissant
     */
    public static int[] sort(int[] before) {
        int[] after = new int[before.length];
        for (int i = 0; i < before.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j : before) max = (max < j) ? j : max;
            int j = 0;
            boolean notFound = true;
            while (j < before.length && notFound) {
                if (before[j] == max) {
                    notFound = false;
                    before[j] = Integer.MIN_VALUE;
                }
                j++;
            }
            after[before.length - (i + 1)] = max;
        }
        return after;
    }

    /**
     * Converti une phrase en une liste des mots qui la compose
     *
     * @param s
     * @return
     */
    public static String[] phraseToMots(String s) {
        ArrayList<String> mots = new ArrayList<>();
        while (s.contains(" ")) {
            String mot = s.substring(0, s.indexOf(" "));
            mots.add(mot);
            s = s.substring(s.indexOf(" ") + 1);
        }
        String[] result = new String[mots.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = mots.get(i);
        }
        return result;
    }


    public static int nbOccur(String s, String test) {
        int cpt = 0;
        for (int i = 0; i < test.length(); i++) {
            String c = String.valueOf(test.charAt(i));
            while (s.contains(c)) {
                cpt++;
                String before = s.substring(0, s.indexOf(c));
                String after = s.substring(s.indexOf(c) + 1);
                s = before + after;
            }
        }
        return cpt;
    }

    public static boolean occur(String s, String test) {
        boolean occur = false;
        for (int i = 0; i < test.length() && !occur; i++) {
            String c = String.valueOf(test.charAt(i));
            int cpt = 0;
            while (s.contains(c) && !occur) {
                cpt++;
                String before = s.substring(0, s.indexOf(c));
                String after = s.substring(s.indexOf(c) + 1);
                s = before + after;
                if (cpt >= 2) {
                    occur = true;
                }
            }
        }
        return occur;
    }

    public static double median(int[] l) {
        l = sort(l);
        if (l.length % 2 == 0) {
            return (double) (l[l.length / 2] + l[l.length / 2 + 1]) / 2;
        } else {
            return l[l.length / 2];
        }
    }

    public static double average(int[] l) {
        int average = 0;
        for (int value : l)
            average += value;
        return (double) average / l.length;
    }

    public static String caesarCode(String original, int offset) {
        StringBuilder crypto = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);
            for (char value : alphabetChar) {
                if (c == value) {
                    c = alphabetChar[(Character.hashCode(c) - 97 + offset) % 26];
                    break;
                }
            }
            crypto.append(c);
        }
        return crypto.toString();
    }

    public static int randomInto(int min, int max) {
        return new Random().nextInt(max) + min;
    }

}