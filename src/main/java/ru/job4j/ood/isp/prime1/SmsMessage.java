package ru.job4j.ood.isp.prime1;

public class SmsMessage implements IMessage {
    @Override
    public void send() {

    }

    @Override
    public String text() {
        return null;
    }

    @Override
    public String subject() {
        throw new IllegalArgumentException();
    }

    @Override
    public String toAddress() {
        return null;
    }

    @Override
    public String fromAddress() {
        return null;
    }
}
