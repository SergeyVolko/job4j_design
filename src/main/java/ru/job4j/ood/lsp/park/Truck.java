package ru.job4j.ood.lsp.park;

public class Truck extends Machine {

    public Truck(String name, int size) {
        super(name, size);
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("Truck size must be more then 1");
        }
    }
}
