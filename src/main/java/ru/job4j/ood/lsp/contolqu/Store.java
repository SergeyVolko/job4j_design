package ru.job4j.ood.lsp.contolqu;

import java.util.Date;
import java.util.List;

public interface Store {
    boolean add(Food food, Date addDate);

    List<Food> getFoods();

    void removeFoods();
}
