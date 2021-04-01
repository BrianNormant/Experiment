import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chaine de caractère a convertir");
        String s = scanner.nextLine();
        System.out.println(CustomMethod.cesarCode(s,3));
    }
}
