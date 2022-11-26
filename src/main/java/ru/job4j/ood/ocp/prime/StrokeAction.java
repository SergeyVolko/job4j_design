package ru.job4j.ood.ocp.prime;

public class StrokeAction implements ActionAnimals {
    @Override
    public String actionOnAnimal(Animal animal) {
        return "Stroke this" + animal.answerAction();
    }
}
