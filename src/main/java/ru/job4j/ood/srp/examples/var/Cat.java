package ru.job4j.ood.srp.examples.var;

public class Cat {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCat(StoreCat store) {
        store.add(this);
    }
}
