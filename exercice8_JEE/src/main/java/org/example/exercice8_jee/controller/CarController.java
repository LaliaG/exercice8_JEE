package org.example.exercice8_jee.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.exercice8_jee.model.Car;
import org.example.exercice8_jee.service.CarService;

import java.util.List;

@Path("/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarController {

    @Inject
    private CarService carService;

    @GET
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") int id) {
        return carService.getCarById(id)
                .map(car -> Response.ok(car).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response addCar(Car car) {
        Car newCar = carService.addCar(car);
        return Response.status(Response.Status.CREATED).entity(newCar).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCar(@PathParam("id") int id, Car car) {
        return carService.updateCar(id, car)
                .map(updatedCar -> Response.ok(updatedCar).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") int id) {
        if (carService.deleteCar(id)) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}




/*package org.example.exercice8_jee.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.exercice8_jee.model.Car;

import java.util.ArrayList;
import java.util.List;

@Path("/cars")
public class CarController {
    @GET
    @Path("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getOneCar() {
        return new Car(1, "Toyota", 2020, "Red");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(1, "Toyota", 2020, "Red"));
        cars.add(new Car(2, "Honda", 2019, "Blue"));
        cars.add(new Car(3, "Ford", 2018, "Black"));
        return cars;
    }

}*/
