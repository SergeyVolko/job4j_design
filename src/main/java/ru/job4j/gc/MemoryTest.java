package ru.job4j.gc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.io.*;

public class MemoryTest {
    public static void main(String[] args) throws IOException {
        String name = "Alex";
        User user = new User(name, 20);
        File file = new File("src/main/java/ru/job4j/gc/explanation.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(ClassLayout.parseInstance(user).toPrintable() + "\n");
            writer.write(ClassLayout.parseInstance(name).toPrintable() + "\n");
            writer.write(ClassLayout.parseInstance(name.getBytes()).toPrintable() + "\n");
            writer.write(GraphLayout.parseInstance(user).toFootprint() + "\n");
            writer.write("Total size: " + GraphLayout.parseInstance(user).totalSize() + "\n");
            writer.write(ClassLayout.parseInstance(new Empty()).toPrintable() + "\n");
        }
    }
}
