package sudoku;

import java.util.Scanner;

public class Sudoku {
    public static final double difficulty = 0.3;
    public static final String RULES = "\tRules and controls: \n" +
            "1: All line, column, line and grid have and of each number between 1 and 9\n" +
            "2: To play, type place+ number you want to add+ position x(1->9)+ position y(1->9)\n" +
            "3: To see the sudoku, type display\n" +
            "4: To exit, type stop\n" +
            "5: To see this again type rules\n" +
            "6: To stop, type stop\n" +
            "7: To refill the grid, type replay\n" +
            "8: Dont forget the case!\n";
    static Integer[][] sudoku = new Integer[9][9];
    public static void main(String[] args) {
        System.out.println("Hello, welcome to sudoku\n"+RULES+"Good game");
        //randomFiller();
        preWrite();
        play();
        System.out.println("GoodBye");
    }
    static void preWrite() {
        int[][] sud =

                {	{ 7,0,0, 0,9,5, 4,0,8 },
                        { 0,0,0, 8,0,4, 3,7,0 },
                        { 0,0,0, 0,0,0, 0,5,1 },

                        { 3,7,0, 4,1,2, 0,0,0 },
                        { 0,4,0, 0,0,0, 0,2,0 },
                        { 0,0,0, 5,3,9, 0,4,6 },

                        { 6,8,0, 9,0,0, 0,0,0 },
                        { 0,5,1, 2,0,3, 0,0,0 },
                        { 2,0,3, 6,4,0, 0,0,7 }
                };
        for (int i = 0; i < sudoku.length; i++) for (int j = 0; j < sudoku[i].length; j++) {
            Integer element = (sud[i][j] != 0)?sud[i][j]: null;
            if (sudoku[i][j] == null && check(element, j, i)) sudoku[i][j] = element;
        }
    }
    static void randomFiller() {
        for (int i = 0; i < sudoku.length; i++) for (int j = 0; j < sudoku[i].length; j++) {
            double rnd = Math.random();
            if (rnd < difficulty) {
                int element = (int) (Math.random() * 10);
                if (sudoku[i][j] == null && check(element, j, i)) sudoku[i][j] = element;
            }
        }
    }
    static boolean check(int element, int x, int y) {
        try {
            boolean toReturn = true;
            if (!isColumnValid(element, y)) toReturn = false;
            if (!isLineValid(element, x)) toReturn = false;

            if (!isGridValid(element, x / 3, y / 3)) toReturn = false;
            return toReturn;
        } catch (NullPointerException e) {
            return false;
        }
    }
    static boolean isLineValid(int element, int line) {
        String lineElement = "";
        for (int i = 0; i < 9; i++) lineElement += sudoku[line][i];
        return !lineElement.contains(String.valueOf(element));
    }
    static boolean isColumnValid(int element, int column) {
        String columnElement = "";
        for (int i = 0; i < 9; i++) columnElement += sudoku[i][column];
        return !columnElement.contains(String.valueOf(element));
    }
    static boolean isGridValid(int element, int xGrid, int yGrid) {//grid is used right to left and up to down; x 0->2, y 0->2
        String gridElement = "";
        for (int i = 3*yGrid; i < 3*yGrid+3; i++) {//y
            for (int j = 3*xGrid; j < 3*xGrid+3; j++) {//x
                gridElement += sudoku[i][j];
            }
        }
        return !gridElement.contains(String.valueOf(element));
    }
    static void printG() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] != null) {
                    System.out.print(sudoku[i][j]+" ");
                } else {
                    System.out.print("  ");
                }
                if ((j+1)%3 == 0) System.out.print("\t");
            }
            System.out.print("\n");
            if ((i+1)%3 == 0) System.out.print("\n");
        }
    }
    static void play() {
        Scanner scanner = new Scanner(System.in);
        boolean playerPlay = true;

        do {
            switch (scanner.next()) {
                case "stop"-> playerPlay = false;
                case "display" -> printG();
                case "replay" -> randomFiller();
                case "rules" -> System.out.print(RULES);
                case "place"-> {
                    int element,x,y;
                    element = Integer.parseInt(scanner.next());
                    x = Integer.parseInt(scanner.next())-1;
                    y = Integer.parseInt(scanner.next())-1;
                    if (between0and9(x)&& between0and9(element)&& between0and9(y)) {
                        if (check(element,x,y)) {
                            sudoku[y][x] = element;
                            System.out.println("Good move");
                            int caseLeft = 0;
                            for (Integer[] i : sudoku) {
                                for (Integer j: i) {
                                    caseLeft += (j==null)?1:0;
                                }
                            }
                            System.out.println(caseLeft+" case left");
                        } else System.out.println("Bad move");
                    } else System.out.println("Type 3 number between 1 and 9");
                }
            }
        } while (playerPlay);
    }
    static boolean between0and9(int number) {
        return (number >= 0 && number <= 9);
    }
}
