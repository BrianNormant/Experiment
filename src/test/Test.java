package test;

import dataStruct.File;

public class Test {
    public static void main(String[] args) {
        File<Integer> file= new File();
        for (int i = 0; i < 5; i++) {
            file.add(i);
        }
        System.out.println("ToString");
        System.out.println(file);
        System.out.println(".ForEach");
        file.forEach(System.out::println);
        System.out.println("Manual for (int i : file)");
        for (int i : file) System.out.println(i);
    }
}
