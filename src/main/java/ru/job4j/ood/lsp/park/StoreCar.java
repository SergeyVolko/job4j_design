package ru.job4j.ood.lsp.park;

public interface StoreCar<T> {
    boolean park(T car);
    boolean toLeave(T car);
}
