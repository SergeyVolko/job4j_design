package ru.job4j.io.lectoresio.shildnio;
/* Эта программа выводит текстовый файл, используя код
потокового ввода-вывода на основе системы NIO.
Чтобы воспользоваться этой программой, укажите имя
файла, который требуется просмотреть. Например, чтобы
просмотреть файл TEST.TXT, введите в режиме командной
строки следующую команду:
java ShowFile TEST.TXT
*/

import java.io.*;
import java.nio.file.*;

public class ShowFile {
    public static void main(String[] args) {
        int i;
        //сначало удостоверится, что указано имя файла
        if (args.length != 1) {
            System.out.println("Пpимeнeниe: ShowFile имя_файла");
            return;
        }
        // открыть файл и получить связанный с ним поток ввода-вывода
        try (InputStream fin = Files.newInputStream(Paths.get(args[0]))) {
            do {
                i = fin.read();
                if (i != -1) {
                    System.out.print((char) i);
                }
            } while (i != -1);
        } catch (InvalidPathException e) {
            System.out.println("Ошибка указания пути " + e);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода " + e);
        }
    }
}
