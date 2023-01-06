package ru.job4j.ood.lsp.park;

public interface Parking<T> {
    boolean park(T car);
    boolean leave(T car);
}
