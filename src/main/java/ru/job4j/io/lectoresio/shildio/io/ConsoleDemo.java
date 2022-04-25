package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение класса Console
import java.io.*;

public class ConsoleDemo {
    public static void main(String[] args) {
        String str;
        Console con;
        // получить ссылку на консоль
        con = System.console();
        // выйти из программы, если консоль недоступна
        if (con == null) {
            return;
        }
        // прочитать строку и вывести ее
        str = con.readLine("Bвeдитe строку: ");
        con.printf("Введенная вами строка: %s\n", str);
    }
}
