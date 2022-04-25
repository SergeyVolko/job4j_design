package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение классов
// DatainputStream и DataOutputStream
import java.io.*;

public class DataIODemo {
    public static void main(String[] args) {
        // сначала вывести данные в файл
        String name = "src/main/java/ru/job4j/io/lectoresio/shildio/io/Test.dat";
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream(name))) {
            dout.writeDouble(98.6);
            dout.writeInt(1000);
            dout.writeBoolean(true);
        } catch (FileNotFoundException e) {
            System.out.println("Heльзя открыть файл вывода");
            return;
        } catch (IOException e) {
            System.out.println("Oшибкa ввода-вывода: " + e);
        }

        // а теперь ввести данные из файла
        try (DataInputStream din = new DataInputStream(new FileInputStream(name))) {
            double d = din.readDouble();
            int i = din.readInt();
            boolean b = din.readBoolean();
            System.out.println("Пoлyчaeмыe значения: "
                    + d + " " + i + " " + b);
        } catch (FileNotFoundException e) {
            System.out.println("Heльзя открыть файл ввода ");
            return;
        } catch (IOException e) {
            System.out.println("Omибкa ввода-вывода: " + e);
        }
    }
}
