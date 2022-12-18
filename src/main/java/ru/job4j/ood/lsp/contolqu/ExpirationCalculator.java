package ru.job4j.ood.lsp.contolqu;

public interface ExpirationCalculator<T> {
    double calculate(T begin, T end, T add);
}
