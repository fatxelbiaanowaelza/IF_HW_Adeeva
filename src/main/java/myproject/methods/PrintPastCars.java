package myproject.methods;

import myproject.cars.Car;

import java.util.List;

public class PrintPastCars {
    public static void printPastCars(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).maxSpeed() > 250) {
                System.out.println("Быстрая машина: " + cars.get(i).getInfo());
            }
        }
    }
}
