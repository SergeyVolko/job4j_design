package ru.job4j.ood.lsp.prme1;

public class VisionTV {
    public static void main(String[] args) {
        TVProgram program = new AdultProgram();
        program.viewing(new TVViewer(16, "Ivan"));
    }
}
