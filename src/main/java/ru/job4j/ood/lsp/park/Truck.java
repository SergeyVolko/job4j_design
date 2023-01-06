package ru.job4j.ood.lsp.park;

import java.util.Objects;

public class Truck extends Machine {

    public Truck(String name, int size) {
        super(name, size);
        if (size <= 1) {
            throw new IllegalArgumentException();
        }
    }
}
