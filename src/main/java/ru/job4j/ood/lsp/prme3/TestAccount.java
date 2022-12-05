package ru.job4j.ood.lsp.prme3;

public class TestAccount {
    public static void main(String[] args) {
        Account account = new MicroAccount(1000);
        account.setCapital(-100);
        System.out.println(account.getCapital());
    }
}
