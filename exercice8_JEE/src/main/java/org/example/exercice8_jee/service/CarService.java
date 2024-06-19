package org.example.exercice8_jee.service;

import org.example.exercice8_jee.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarService {
    private List<Car> cars = new ArrayList<>();
    private int currentId = 1;

    public CarService() {
        // Initial seed data
        cars.add(new Car(currentId++, "Peugeot", 2001, "yellow"));
        cars.add(new Car(currentId++, "Toyota", 2020, "red"));
        cars.add(new Car(currentId++, "Renault", 2015, "blue"));
        cars.add(new Car(currentId++, "Honda", 2010, "green"));
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public Optional<Car> getCarById(int id) {
        return cars.stream().filter(car -> car.getId() == id).findFirst();
    }

    public Car addCar(Car car) {
        car.setId(currentId++);
        cars.add(car);
        return car;
    }

    public Optional<Car> updateCar(int id, Car updatedCar) {
        return getCarById(id).map(existingCar -> {
            existingCar.setBrand(updatedCar.getBrand());
            existingCar.setYear(updatedCar.getYear());
            existingCar.setColor(updatedCar.getColor());
            return existingCar;
        });
    }

    public boolean deleteCar(int id) {
        return cars.removeIf(car -> car.getId() == id);
    }
}
