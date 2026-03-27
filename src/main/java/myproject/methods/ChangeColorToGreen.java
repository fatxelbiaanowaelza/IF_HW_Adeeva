package myproject.methods;

import myproject.cars.Car;

import java.util.List;

public class ChangeColorToGreen {
    public static void changeColorToGreen(List<Car> cars) {
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getColor().equalsIgnoreCase("зеленый")) {
                cars.get(i).setColor("Красный");
            }
        }
    }
}
