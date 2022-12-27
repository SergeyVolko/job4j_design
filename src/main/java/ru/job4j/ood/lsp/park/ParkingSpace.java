package ru.job4j.ood.lsp.park;

import java.util.*;

public class ParkingSpace implements StoreCar<Car> {

    enum TypeCar {
        PASSENGER, CARGO
    }

    private int passSpace;
    private int cargoSpace;
    private final Map<Car, TypeCar> cars = new HashMap<>();

    public ParkingSpace(int passSpace, int cargoSpace) {
        this.passSpace = passSpace;
        this.cargoSpace = cargoSpace;
    }

    @Override
    public boolean park(Car car) {
        return false;
    }

    @Override
    public boolean toLeave(Car car) {
        return false;
    }
}
