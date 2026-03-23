package myproject.cars;

import java.util.List;

public class CarService {
    public static void printNewCar(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getYear().getYear() > 2006) {
                System.out.println(cars.get(i).getInfo());
            } else {
                System.out.println("«устаревший авто»");
            }
        }
    }

    public static void changeColorToGreen(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getColor().equalsIgnoreCase("зеленый")) {
                cars.get(i).setColor("Красный");
            }
        }
    }

    public static void printPastCars(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).maxSpeed() > 250) {
                System.out.println("Быстрая машина: " + cars.get(i).getInfo());
            }
        }
    }
}
