package ru.job4j.gc.clone;

public class InnerObject implements Cloneable {
    int num;

    @Override
    protected InnerObject clone() throws CloneNotSupportedException {
        return (InnerObject) super.clone();
    }
}
