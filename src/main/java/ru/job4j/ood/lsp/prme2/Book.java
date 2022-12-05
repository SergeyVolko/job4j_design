package ru.job4j.ood.lsp.prme2;

public class Book {
    private String author;
    private String name;
    private double price;

    public Book(String author, String name, double price) {
        this.author = author;
        this.name = name;
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
