package ru.job4j.ood.lsp.contolqu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();
    private final String name;
    protected ExpirationCalculator<Date> calculator;

    public AbstractStore(String name, ExpirationCalculator<Date> calculator) {
        this.name = name;
        this.calculator = calculator;
    }

    public abstract boolean add(Food food, Date addDate);

    protected boolean checkExpiration(Food food, BiPredicate<Food, Double> predicate, Date addDate) {
        return predicate.test(food, calculator.calculate(food.getCreateDate(), food.getExpiryDate(), addDate))
                && foods.add(food);
    }

    @Override
    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }

    @Override
    public void removeFoods() {
        foods.clear();
    }
    public String getNameStore() {
        return name;
    }
}
