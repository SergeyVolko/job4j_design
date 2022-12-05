package ru.job4j.ood.lsp.prme2;

public class ShopBook {
    protected double discount;
    protected double priceForDiscount;

    public ShopBook(double discount, double priceForDiscount) {
        this.discount = discount;
        this.priceForDiscount = priceForDiscount;
    }

    public double getCost(Book book) {
        double price = book.getPrice();
        return price >= priceForDiscount ? price * discount * 0.01 : book.getPrice();
    }
}
