package ru.job4j.ood.lsp.contolqu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiPredicate;

public abstract class AbstractStore implements Store {
    private List<Food> foods = new ArrayList<>();
    private String nameStore;

    public AbstractStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public abstract boolean add(Food food, Date addDate);

    private double getPercent(Food food, Date addDate) {
        long addDateMs = addDate.getTime();
        long create = food.getCreateDate().getTime();
        long expire = food.getExpiryDate().getTime();
        if (addDateMs < create) {
            throw new IllegalArgumentException("Invalid date of addition.");
        }
        return (double) (addDateMs - create) / (expire - create) * 100;
    }

    public boolean addFood(Food food, BiPredicate<Food, Double> predicate, Date addDate) {
        if (addDate.getTime() < food.getCreateDate().getTime()) {
            throw new IllegalArgumentException("Invalid date of addition.");
        }
        return predicate.test(food, getPercent(food, addDate)) && foods.add(food);
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }
}
