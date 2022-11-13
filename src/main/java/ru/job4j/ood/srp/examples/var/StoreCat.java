package ru.job4j.ood.srp.examples.var;

import java.util.ArrayList;
import java.util.List;

public class StoreCat {
    private static final StoreCat INSTANCE  = new StoreCat();
    private List<Cat> cats = new ArrayList<>();

    private StoreCat() {

    }

    public static StoreCat getInstance() {
        return INSTANCE;
    }

    public void add(Cat cat) {
        cats.add(cat);
    }
}
