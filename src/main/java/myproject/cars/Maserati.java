package myproject.cars;

import java.time.LocalDate;

public class Maserati extends Car {

    public Maserati(String model, LocalDate year, String brand, String color, String transmission, String engine, String fuleType, String driveType, int mileage) {
        super(model, year, brand, color, transmission, engine, fuleType, driveType, mileage);
    }

    @Override
    public int maxSpeed() {
        return 326;
    }
}