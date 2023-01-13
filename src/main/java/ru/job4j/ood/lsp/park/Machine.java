package ru.job4j.ood.lsp.park;

import java.util.Objects;

public abstract class Machine {

    private final String name;
    private final int size;

    public Machine(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Machine machine = (Machine) o;
        return size == machine.size && Objects.equals(name, machine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
