package sudoku;

import java.util.Scanner;

public class Ex1 {
    final static String alphabet = "123456789";
    public static void main(String[] args) {
        int lig, col, element, nbCasesVides;
        boolean bonCoup;

        int nbChiffres[];			// vecteur de 10 compteurs
        nbChiffres = new int[10];

        int sud[][] =

                {	    { 7,0,0, 0,9,5, 4,0,8 },
                        { 0,0,0, 8,0,4, 3,7,0 },
                        { 0,0,0, 0,0,0, 0,5,1 },

                        { 3,7,0, 4,1,2, 0,0,0 },
                        { 0,4,0, 0,0,0, 0,2,0 },
                        { 0,0,0, 5,3,9, 0,4,6 },

                        { 6,8,0, 9,0,0, 0,0,0 },
                        { 0,5,1, 2,0,3, 0,0,0 },
                        { 2,0,3, 6,4,0, 0,0,7 }
                };

        // Afficher la grille de Sudoku	selon en respectant la présentation demandée
        // dans l'énoncé du travail.
        print(sud);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ajouter un nombre");
        System.out.print("Ligne = ");lig = scanner.nextInt();System.out.print("\n");
        System.out.print("Colonne = ");col = scanner.nextInt();System.out.print("\n");
        System.out.print("Element = ");element = scanner.nextInt();System.out.print("\n");
        sud[lig][col] = element;
        // Déterminer s'il y a une valeur qui se répète dans une ligne
        boolean lineValid;
        {
            int i = 0;
            String line= "";
            do {
                for (int j = 0; j < sud[0].length; j++) line += sud[i][j];
                lineValid = verifyOccurrence(line);
                i++;
            } while (!lineValid);
        }
        // Déterminer s'il y a une valeur qui se répète dans une colonne
        boolean columnValid;
        {
            int i = 0;
            String line= "";
            do {
                for (int j = 0; j < sud[0].length; j++) line += sud[j][i];
                columnValid = verifyOccurrence(line);
                i++;
            } while (!columnValid);
        }
        // Déterminer s'il y a une valeur qui se répète dans un carré
        boolean gridValid;
        {
            int xGrid = 0, yGrid = 0;
            String gridElement = "";
            do {
                for (int i = 3*yGrid; i < 3*yGrid+3; i++) {//y
                    for (int j = 3*xGrid; j < 3*xGrid+3; j++) {//x
                        gridElement += sud[i][j];
                    }
                }
                gridValid = verifyOccurrence(gridElement);
            } while (!gridValid);
        }
        // Déterminer si la partie est finie
        int k = 0;
        if (lineValid && gridValid && columnValid) {
            System.out.println("Bon Coup");
            k = 1;
        } else System.out.println("Mauvais coup");
        int caseLeft = 0;
        for (int[] i : sud) {
            for (Integer j: i) {
                caseLeft += (j==0)?1:0;
            }
        }
        System.out.println("Il reste "+(caseLeft-k)+" cases vide");
    }
    static void print(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]+" ");
                if ((j+1)%3 == 0) System.out.print("\t");
            }
            System.out.print("\n");
            if ((i+1)%3 == 0) System.out.print("\n");
        }
    }
    public static boolean verifyOccurrence(String toTest) {
        boolean occurrence;
        int i = 0;
        String temp;
        do {
            temp = toTest.replace(String.valueOf(alphabet.charAt(i)),"");
            occurrence = temp.length() < toTest.length() - 1;
            i++;
        } while (!occurrence && i < alphabet.length());

        return occurrence;
    }
}
