package ru.job4j.ood.lsp.park;

import java.util.Objects;

public class Truck extends Car {

    private final int size;

    public Truck(String name, int size) {
        super(name);
        if (size <= 1) {
            throw new IllegalArgumentException();
        }
        this.size = size;
    }

    @Override
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
        if (!super.equals(o)) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "name='"
                + name + '\''
                + ", size=" + size
                + '}';
    }
}
