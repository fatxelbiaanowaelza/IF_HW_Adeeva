package myproject.methods;

import myproject.cars.Car;

import java.util.List;

public class PrintNewCar {
    public static void printNewCar(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getYear().getYear() > 2006) {
                System.out.println(cars.get(i).getInfo());
            } else {
                System.out.println("«устаревший авто»");
            }
        }
    }
}
