import java.util.ArrayList;
import java.util.Arrays;
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
     * Sort a list by ascending
     *
     * @param before the unsort list
     * @return the same but sort list
     */
    public static int[] sort(int[] before) {
        int[] after = new int[before.length];
        for (int i = 0; i < before.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j : before) max = Math.max(max, j);
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
     * suppress multiple occurence in a list
     * @param tab the list to compute
     * @return the same list without the occurence
     */
    public static Object[] suppressDouble(Object[] tab) {
        ArrayList<Object> tab2 = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            int a = 0;
            for (Object j : tab) {
                if (j.equals(i)) a++;
                if (a >= 2) break;
            }
            if (a <= 1) tab2.add(i);
        }
        return tab2.toArray();
    }

    /**
     * Find the largest number in a list
     * @param tab the list to compute
     * @return the largest number
     */
    public static int max(int[] tab) {
        int max = 0;
        for (int i : tab) max = Math.max(i, max);
        return max;
    }

    /**
     * find the index of the minest number in a list
     * @param tab the list to compute
     * @return the minest number
     */
    public static int indexOfMax(int[] tab) {
        int max = 0;
        for (int i : tab) max = Math.max(i, max);
        for (int i = 0; i < tab.length; i++) if (max == tab[i]) return i;
        return -1;
    }

    /**
     * find the index of the minest number in a list
     * @param tab the list to compute
     * @return the index of the minest number of the list
     */
    public static int indexOfMin(int[] tab) {
        int min = 0;
        for (int i : tab) min = Math.max(i, min);
        for (int i = 0; i < tab.length; i++) if (min == tab[i]) return i;
        return -1;
    }

    /**
     * find the minest number in a list
     * @param tab the list to compute
     * @return the minest number
     */
    public static int min(int[] tab) {
        int max = 0;
        for (int i : tab) max = Math.min(i, max);
        return max;
    }

    /**
     * search if the number is in a list
     * @param tab the list to search on
     * @param k the number to test
     * @return if the number is in the list
     */
    public static boolean exist(int[] tab, int k) {
        for (int i = 0; i < tab.length; i++) if (tab[i] == k) return true;
        return false;
    }

    /**
     * An optimized method of finding if the list in sort by ascending
     * @param tab the list where to try to find
     * @param k the number to find in the list
     * @return if the number exist in the list
     */
    public static boolean existSorted(int[] tab, int k) {
        if (tab.length == 1) return tab[0] == k;
        int[] tab2 = new int[tab.length + ((tab.length%2 == 0)?0:1)];
        if (k <= tab[tab2.length-1]) {
            for (int i = 0; i < tab2.length; i++) tab2[i] = tab[i];
        } else for (int i = 0; i < tab2.length; i++) tab2[i] = tab[i + tab2.length-((tab.length%2 != 0)?1:0)];
        return exist(tab2,k);
    }

    /**
     * find the closest number to zero in a list, if there are two number x and -x
     * the method return x
     * @param tab the list to search on
     * @return return the closest number to zero
     */
    public static int closestToZero(int[] tab) {
        System.out.println(Arrays.toString(tab));
        int closest = tab[0];
        for (int i : tab) {
            if (i == 0) {
                closest = 0;
                break;
            }
            if (Math.abs(closest) > Math.abs(i)) closest = (i - closest == 0)?Math.abs(i):i;
        }
        return closest;
    }

    /**
     * Split a String into a array of word.
     * the word is determined by space <code>" "</code>
     * @param s The String to split
     * @return an array of the word in the String
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

    /**
     * search the number of occurence of a number in a String
     * @param s the String to search on
     * @param test the String to search
     * @return the number of occurence of test
     */
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

    /**
     * test if a String appear in another String
     * @param s the String to search on
     * @param test the Strign to search
     * @return if test exist in s
     */
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

    /**
     * Compute the median of a list
     * @param l the list to compute
     * @return the median of the list
     */
    public static double median(int[] l) {
        l = sort(l);
        if (l.length % 2 == 0) {
            return (double) (l[l.length / 2] + l[l.length / 2 + 1]) / 2;
        } else {
            return l[l.length / 2];
        }
    }

    /**
     * Compute the average value of the list
     * @param l the list to compute
     * @return the average
     */
    public static double average(int[] l) {
        int average = 0;
        for (int value : l)
            average += value;
        return (double) average / l.length;
    }

    /**
     * Script a String with the ceasar method
     * @param original The String to script
     * @param offset the parameter of the script
     * @return the scripted String
     */
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

    /**
     * Generate a random number into two limits
     * @param min the min number
     * @param max the max number
     * @return a random number betwen min and max
     */
    public static int randomInto(int min, int max) {
        return new Random().nextInt(max) + min;
    }

}