package ru.job4j.ood.isp.prime1;

public interface IMessage {
    void send();
    String text();
    String subject();
    String toAddress();
    String fromAddress();
}
