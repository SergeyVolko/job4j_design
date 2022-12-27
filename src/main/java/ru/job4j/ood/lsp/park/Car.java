package ru.job4j.ood.lsp.park;

import java.util.Objects;

public class Car {
    private final String name;
    private final String regNum;
    private final int size;

    public Car(String name, String regNum, int size) {
        this.name = name;
        this.regNum = regNum;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getRegNum() {
        return regNum;
    }

    public int getSize() {
        return size;
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
        return size == car.size && Objects.equals(name, car.name)
                && Objects.equals(regNum, car.regNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regNum, size);
    }
}
