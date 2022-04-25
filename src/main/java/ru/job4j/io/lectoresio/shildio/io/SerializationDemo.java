package ru.job4j.io.lectoresio.shildio.io;
// Продемонстрировать применение сериализации и десериализации
import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        // произвести сериализацию объекта
        String name = "src/main/java/ru/job4j/io/lectoresio/shildio/io/serial.txt";
        try (ObjectOutputStream objOStrm =
                new ObjectOutputStream(new FileOutputStream(name))) {
            MyClass object1 = new MyClass("Hello", -7, 2.7e10);
            System.out.println("object1: " + object1);
            objOStrm.writeObject(object1);
        } catch (IOException e) {
            System.out.println("Исключение при сериализации " + e);
        }
        // произвести десериализацию объекта
        try (ObjectInputStream objIStrm =
                     new ObjectInputStream(new FileInputStream(name))) {
            MyClass object2 = (MyClass) objIStrm.readObject();
            System.out.println("object2: " + object2);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Исключение при десериадизации " + e);
            System.exit(0);
        }
    }
}
