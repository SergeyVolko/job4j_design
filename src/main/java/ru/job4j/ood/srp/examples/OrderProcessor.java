package ru.job4j.ood.srp.examples;

public interface OrderProcessor {

    void process(Order order);

    boolean save(Order order);

    void sendConfirmationEmail(Order order);
}
