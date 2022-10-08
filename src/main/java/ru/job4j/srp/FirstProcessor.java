package ru.job4j.srp;

public class FirstProcessor implements OrderProcessor {

    @Override
    public void process(Order order) {
        SimpleHandle handlerOrder = new SimpleHandle();
        handlerOrder.processing(order);
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public void sendConfirmationEmail(Order order) {

    }
}
