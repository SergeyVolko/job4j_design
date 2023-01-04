package ru.job4j.ood.lsp.park;

import java.util.Objects;

public abstract class Car {
    String name;

    public Car(String name) {
        this.name = name;
    }

    public abstract int getSize();

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
