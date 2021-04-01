package test;

import java.io.File;
import java.io.IOException;

public class Test {
    public static final String root;
    static {
        File file = new File("test");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        root = file.getAbsolutePath().replace(file.getName(),"");
    }
    public static void main(String[] args) {
        Car[] cars = new Car[5];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car("Mercedes");
        }
        Car first = new Car("Lamborgini",200);
        for (Car car : cars) System.out.println(car);
        System.out.println(first);
        System.out.println("There are "+Car.numberOfCars+" cars");

        cars[1].crashInto(cars[3]);
        System.out.println(cars[1]);
        System.out.println(cars[3]);
    }
    public static void show(Object... ss) {
        for (Object s : ss) System.out.println(s);
    }
    public static void show(String prefix, Object... ss) {
        for (Object s : ss) System.out.println(prefix+s);
    }
    static class Car {
        private static int numberOfCars = 0;
        public int getNumberOfCars() {
            return numberOfCars;
        }
        private int fuel;
        private final int id;
        private String brand;
        Car(String brand) {
            this(brand, 100);
        }
        Car(String brand, int fuel) {
            this.brand = brand;
            this.fuel = fuel;
            this.id = String.valueOf(numberOfCars).hashCode();
            numberOfCars++;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "fuel=" + fuel +
                    ", brand='" + brand + '\'' +
                    ", id="+id+
                    '}';
        }
        public void crashInto(Car car) {
            this.fuel = 0;
            car.fuel = 0;

            this.brand = "crashed";
            car.brand = this.brand;
        }
    }
}
