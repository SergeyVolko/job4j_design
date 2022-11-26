package ru.job4j.ood.ocp.prime;

public class Person2 {

    private Cat cat;
    private StrokeAction action;

    public Person2(Cat cat, StrokeAction action) {
        this.cat = cat;
        this.action = action;
    }

    public void interaction() {
        System.out.println(action.actionOnAnimal(cat));
    }
}
