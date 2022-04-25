package ru.job4j.io.lectoresio.shildio.io;
// Продемострировать применение метода unread()
// из класса PushBackInputStream
import java.io.*;

public class PushbackInputStreamDemo {
    public static void main(String[] args) {
        String s = "if (a == 4) a = 0;\n";
        byte[] buf = s.getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        int c;
        try (PushbackInputStream f = new PushbackInputStream(in);) {
            while ((c = f.read()) != -1) {
                switch (c) {
                    case '=':
                        c = in.read();
                        if (c == '=') {
                            System.out.print(".eq.");
                        } else {
                            System.out.print("<-");
                            f.unread(c);
                        }
                        break;
                    default:
                        System.out.print((char) c);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }
}
