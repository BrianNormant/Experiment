import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CustomMethod {
    public static final int[] fibonaci;
    public static final String consonnes;
    public static final String voyelles = "aeiouy";
    public static final String alphabet;
    public static final char[] alphabetChar = new char[26];
    static {
        for (int i = 0; i < alphabetChar.length; i++) {
            alphabetChar[i] = Character.toChars(i+97)[0];
        }
        String s = "", s2 = "";
        for (char c : alphabetChar) {
            s += c;
            if (!voyelles.contains(String.valueOf(c))) s2 += c;
        }
        alphabet = s;
        consonnes = s2;
    }
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
    public static String cesarCode(String original, int offset) {
        StringBuilder script = new StringBuilder();
        boolean[] wasUpperCase = new boolean[original.length()];
        for (int i = 0; i < original.length(); i++) if (Character.isUpperCase(original.charAt(i))) wasUpperCase[i] = true;
        original = original.toLowerCase();
        for (char c : original.toCharArray()) {
            boolean found = false;
            for (int i = 0; i < alphabetChar.length; i++) {
                if (c == alphabetChar[i]) {
                    script.append(Character.toChars((i + offset) % 26 + 97)[0]);
                    found = true;
                }
            }
            if (!found) script.append(c);
        }
        for (int i = 0; i < original.length(); i++) if (wasUpperCase[i]) {
            script.insert(i,(String.valueOf(script.charAt(i)).toUpperCase()));
            script.deleteCharAt(i+1);
        }
        return script.toString();
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

    /**
     * A recursive method to print the different stage to resolve a diskNumber disk Hanoi problem
     * By default, the intermediate tower is named 'B', assuming the firt and last towers are named A and C
     * @param diskNumber the number of disk
     * @param or the name of the firt tower
     * @param dest the name of the last tower
     */
    public static void hanoiSolver(int diskNumber,char or, char dest) {
        if (diskNumber == 1) {
            System.out.println("Move the top disc from tower "+or+" to tower "+dest);
            return;
        }
        diskNumber--;
        hanoiSolver(diskNumber,'A','B');
        System.out.println("Move the top disc from tower "+or+" to tower "+dest);
        hanoiSolver(diskNumber,'B','C');
    }

}