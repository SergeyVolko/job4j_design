package ru.job4j.io.lectoresio.shildsocket;
//Продемонстрировать применение класса URL
import java.net.*;

public class URLDemo {
    public static void main(String[] args) throws MalformedURLException {
        URL hp = new URL("http://www.HerbSchildt.com/Articles");
        System.out.println("Пpoтoкoл: " + hp.getProtocol());
        System.out.println("Пopт: " + hp.getPort());
        System.out.println("Xocт: " + hp.getHost());
        System.out.println("Фaйл: " + hp.getFile());
        System.out.println("Пoлнaя форма:" + hp.toExternalForm());
    }
}
