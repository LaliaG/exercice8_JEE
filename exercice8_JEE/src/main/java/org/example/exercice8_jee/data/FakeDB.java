package org.example.exercice8_jee.data;

import org.example.exercice8_jee.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDB {
    private static List<Car> cars = new ArrayList<>();

    static {
        // Adding some initial data
        cars.add(new Car(1, "Peugeot", 2001, "yellow"));
        cars.add(new Car(2, "Toyota", 2020, "red"));
        cars.add(new Car(3, "Renault", 2015, "blue"));
        cars.add(new Car(4, "Honda", 2010, "green"));
    }

    public static List<Car> getAllCars() {
        return cars;
    }

    public static Optional<Car> getCarById(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst();
    }

    public static Car addCar(Car car) {
        cars.add(car);
        return car;
    }

    public static Optional<Car> updateCar(int id, Car car) {
        Optional<Car> existingCar = getCarById(id);
        if (existingCar.isPresent()) {
            cars.remove(existingCar.get());
            car.setId(id);
            cars.add(car);
            return Optional.of(car);
        }
        return Optional.empty();
    }

    public static boolean deleteCar(int id) {
        Optional<Car> car = getCarById(id);
        if (car.isPresent()) {
            cars.remove(car.get());
            return true;
        }
        return false;
    }
}
