package myproject.cars;

import java.time.LocalDate;

public abstract class Car {

    /**
     * Модель.
     */
    private String model;
    /**
     * Дата выпуска
     */
    private LocalDate year;
    /**
     * Марка автомобиля
     */
    private String brand;
    /**
     * Цвет автомобиля
     */
    private String color;
    /**
     * Коробка передач (автомат/механика/робот)
     */
    private String transmission;
    /**
     * Тип двигателя (бензин/дизель/автомат)
     */
    private String engine;
    /**
     * Тип топлива
     */
    private String fuleType;
    /**
     * Привод (передний,задний,полный)
     */
    private String driveType;
    /**
     * Пробег автомобиля
     */
    private int mileage;

    public Car(String model, LocalDate year, String brand, String color, String transmission, String engine, String fuleType, String driveType, int mileage) {
        this.model = model;
        this.year = year;
        this.brand = brand;
        this.color = color;
        this.transmission = transmission;
        this.engine = engine;
        this.fuleType = fuleType;
        this.driveType = driveType;
        this.mileage = mileage;
    }

    public String getInfo() {
        return "Модель: " + model + ", " + "Год выпуска: " + year + ", " + "Марка: " + brand + ", " + "Цвет: " + color + ", " + "Коробка передач: " + transmission + ", " + "Тип двигателя: " + engine + ", " + "Тип топлива: " + fuleType + ", " + "Привод: " + driveType + ", " + "Пробег: " + mileage + ".";
    }

    public void startEngine() {
        System.out.println("Двигатель запущен");
    }
    public void stopEngine() {
        System.out.println("Двигатель остановлен");
    }

    public void setColor(String color) {
        this.color = color;
    }
    public abstract int maxSpeed();

    public String getColor() {
        return color;
    }

    public LocalDate getYear() {
        return year;
    }
}

