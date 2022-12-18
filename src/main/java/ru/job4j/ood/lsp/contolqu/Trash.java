package ru.job4j.ood.lsp.contolqu;

import java.util.Date;
import java.util.function.BiPredicate;

public class Trash extends AbstractStore {
    private static final BiPredicate<Food, Double> BI_PREDICATE = (f, p) -> p >= 75;

    public Trash(String nameStore) {
        super(nameStore, new LocalDateTimeExpirationCalculator());
    }

    @Override
    public boolean add(Food food, Date addDate) {
        return addFood(food, BI_PREDICATE, addDate);
    }
}
