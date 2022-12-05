package ru.job4j.ood.lsp.prme2;

public class BookTrade {
    public static void main(String[] args) {
        ShopBook shopBook = new ShopWithoutDiscount(10.0, 1000);
        shopBook.getCost(new Book("Ivanov", "Content", 2000.0));
    }
}
