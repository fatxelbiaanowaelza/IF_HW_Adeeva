package myproject.cars;

import java.time.LocalDate;

public class Toyota extends Car {

    public Toyota(String model, LocalDate year, String brand, String color, String transmission, String engine, String fuleType, String driveType, int mileage) {
        super(model, year, brand, color, transmission, engine, fuleType, driveType, mileage);
    }

    @Override
    public int maxSpeed() {
        return 280;
    }
}