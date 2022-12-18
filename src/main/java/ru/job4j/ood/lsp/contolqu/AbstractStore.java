package ru.job4j.ood.lsp.contolqu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;

public abstract class AbstractStore implements Store {
    private List<Food> foods = new ArrayList<>();
    private String nameStore;
    ExpirationCalculator<Date> calculator;

    public AbstractStore(String nameStore, ExpirationCalculator<Date> calculator) {
        this.nameStore = nameStore;
        this.calculator = calculator;
    }

    public abstract boolean add(Food food, Date addDate);

    public boolean addFood(Food food, BiPredicate<Food, Double> predicate, Date addDate) {
        return predicate.test(food, calculator.calculate(food.getCreateDate(), food.getExpiryDate(), addDate))
                && foods.add(food);
    }

    public List<Food> getFoods() {
        return new ArrayList<>(foods);
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }
}
