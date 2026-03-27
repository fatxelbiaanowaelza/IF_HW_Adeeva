import myproject.cars.*;

import java.time.LocalDate;

import java.util.List;

import static myproject.methods.ChangeColorToGreen.changeColorToGreen;
import static myproject.methods.PrintNewCar.*;
import static myproject.methods.PrintPastCars.printPastCars;

public class Main {
    //it's Main origin
    static void main() {

        Car car1 = new LandRover("Land Rover", LocalDate.of(2022, 2, 2), "Sport",
                "Белый", "Автомат", "Бензиновый", "АИ-100", "Полный", 10200);
        Car car2 = new LandRover("Land Rover", LocalDate.of(2025, 6, 15), "Lelar",
                "Серый", "Автомат", "Бензиновый", "АИ-100", "Полный", 5000);
        Car car3 = new Maserati("Maserati", LocalDate.of(2005, 4, 4), "Levante",
                "Красный", "Автомат", "Бензиновый", "АИ-100", "Передний", 20000);
        Car car4 = new Maserati("Maserati", LocalDate.of(2020, 1, 12), "Ghibli",
                "Чёрный", "Автомат", "Бензиновый", "АИ-100", "Полный", 12000);
        Car car5 = new Nissan("Nissan", LocalDate.of(2019, 8, 8), "N7",
                "Жёлтый", "Автомат", "Бензиновый", "АИ-100", "Задний", 10200);
        Car car6 = new Nissan("Nissan", LocalDate.of(2021, 12, 11), "Gtn",
                "Синий", "Автомат", "Бензиновый", "АИ-98", "Полный", 70000);
        Car car7 = new Subaru("Subaru", LocalDate.of(2022, 2, 2), "Impreza",
                "Голубой", "Автомат", "Бензиновый", "АИ-95", "Полный", 101000);
        Car car8 = new Subaru("Subaru", LocalDate.of(2020, 3, 10), "XV",
                "Белый", "Механика", "Бензиновый", "АИ-92", "Задний", 17000);
        Car car9 = new Toyota("Toyota", LocalDate.of(2010, 7, 4), "Mark",
                "Фиолетовый", "Механика", "Бензиновый", "АИ-92", "Полный", 12000);
        Car car10 = new Volkswagen("Volkswagen", LocalDate.of(2012, 6, 5), "Tiguan",
                "Зеленый", "Механика", "Бензиновый", "АИ-95", "Полный", 18000);

        List<Car> cars = List.of(car1, car2, car3, car4, car5, car6, car7, car8, car9, car10);
        printNewCar(cars);
        changeColorToGreen(cars);
        printPastCars(cars);
    }
}
