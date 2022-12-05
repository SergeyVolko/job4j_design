package ru.job4j.ood.lsp.prme1;

public class AdultProgram extends TVProgram {

    public AdultProgram(int ageViewer) {
        super(ageViewer);
    }

    @Override
    public void viewing(TVViewer viewer) {
        if (viewer.getAge() < 18) {
            throw new IllegalArgumentException();
        }
    }
}
