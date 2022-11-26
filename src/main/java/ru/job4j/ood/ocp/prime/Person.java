package ru.job4j.ood.ocp.prime;

public class Person {

    public void interaction() {
        StrokeAction strokeAction = new StrokeAction();
        Cat cat = new Cat();
        System.out.println(strokeAction.actionOnAnimal(cat));
    }
}
