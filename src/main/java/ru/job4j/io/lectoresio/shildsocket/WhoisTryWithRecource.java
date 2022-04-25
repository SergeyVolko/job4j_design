package ru.job4j.io.lectoresio.shildsocket;
// Использовать блок оператора try с ресурсами для закрытия сокета
import java.net.*;
import java.io.*;

public class WhoisTryWithRecource {
    public static void main(String[] args) throws Exception {
        int c;
        // создать сокетное соединение с веб-сайтом internic.net
        // через порт 43. Этим сокетом управляет блок оператора
        // try с ресурсами
        try (Socket s = new Socket("whois.internic.net", 43)) {
            //получить потоки ввода-вывода
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            //сформировать строку запроса
            String str = (args.length == 0 ? "OraclePressBooks.com" : args[0]) + "\n";

            //преобразовать строку запроса в байты
            byte[] buf = str.getBytes();

            //послать запрос
            out.write(buf);

            //прочитать ответ и вывести его на экран
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        }
        //Теперь сокет закрыт
    }
}
