package ru.job4j.srp;

public interface OrderProcessor {

    void process(Order order);

    boolean save(Order order);

    void sendConfirmationEmail(Order order);
}
