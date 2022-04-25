package ru.job4j.io.lectoresio.videoio.task9;

import java.io.*;

public class Serializ {
    private static final String PATH = "src/main/java/ru/job4j/io/lectoresio/videoio/task9/object.txt";

    public static void main(String[] args) throws ClassNotFoundException {
        serialize();
        deserialize();
    }

    private static void serialize() {
        SerializableClass serializableClass = new SerializableClass(SerializableClass.class.getName(), 0);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH))) {
            outputStream.writeObject(serializableClass);
        } catch (IOException exception) {
            System.out.println("Output error");
        }
    }

    private static void deserialize() throws ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH))) {
            SerializableClass serializableClass = (SerializableClass) inputStream.readObject();
            System.out.printf("Title: %s%nSize: %d", serializableClass.title, serializableClass.size);
        } catch (IOException exception) {
            System.out.println("Input error");
        }
    }
    private static class SerializableClass implements Serializable {
        private String title;
        private int size;

        public SerializableClass(String title, int size) {
            this.title = title;
            this.size = size;
        }
    }

}
