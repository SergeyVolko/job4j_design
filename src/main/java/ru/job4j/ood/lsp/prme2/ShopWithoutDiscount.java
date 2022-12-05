package ru.job4j.ood.lsp.prme2;

public class ShopWithoutDiscount extends ShopBook {

    public ShopWithoutDiscount(double discount, double priceForDiscount) {
        super(discount, priceForDiscount);
    }

    public double getCost(Book book) {
        return book.getPrice();
    }
}
