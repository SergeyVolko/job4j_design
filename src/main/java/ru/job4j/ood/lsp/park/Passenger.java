package ru.job4j.ood.lsp.park;

public class Passenger extends Car {

    private final int size = 1;

    public Passenger(String name) {
        super(name);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Passenger{"
                + "name='"
                + name + '\''
                + ", size="
                + size
                + '}';
    }
}
