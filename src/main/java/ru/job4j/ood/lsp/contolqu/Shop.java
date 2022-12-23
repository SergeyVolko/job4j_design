package ru.job4j.ood.lsp.contolqu;

import java.util.Date;
import java.util.function.BiPredicate;

public class Shop extends AbstractStore {
    private static final BiPredicate<Food, Double> BI_PREDICATE = (f, p) -> p >= 25 && p < 75;

    public Shop(String nameStore) {
        super(nameStore, new LocalDateTimeExpirationCalculator());
    }

    @Override
    public boolean add(Food food, Date addDate) {
        return checkExpiration(food, BI_PREDICATE, addDate);
    }
}
