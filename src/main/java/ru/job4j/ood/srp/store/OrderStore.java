package ru.job4j.ood.srp.store;

import java.util.List;
import java.util.function.Predicate;

public interface OrderStore<T> {
    List<T> getListStore(Predicate<T> filter);
}
