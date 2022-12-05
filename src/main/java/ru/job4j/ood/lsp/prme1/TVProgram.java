package ru.job4j.ood.lsp.prme1;

public class TVProgram {
    public void viewing(TVViewer viewer) {
        if (viewer.getAge() < 16) {
            throw new IllegalArgumentException();
        }
    }
}
