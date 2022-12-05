package ru.job4j.ood.lsp.prme3;

public class Account {
    protected int capital;

    public Account(int capital) {
        if (capital < 0) {
            throw new IllegalArgumentException();
        }
        this.capital = capital;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        if (capital < 0) {
            throw new IllegalArgumentException();
        }
        this.capital = capital;
    }
}
