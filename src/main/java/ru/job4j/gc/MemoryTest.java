package ru.job4j.gc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

public class MemoryTest {
    public static void main(String[] args) {
        String name = "Alex";
        User user = new User(name, 20);
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        System.out.println(ClassLayout.parseInstance(name).toPrintable());
        System.out.println(ClassLayout.parseInstance(name.getBytes()).toPrintable());
        System.out.println(GraphLayout.parseInstance(user).toFootprint());
        System.out.println("Total size: " + GraphLayout.parseInstance(user).totalSize());
        System.out.println(ClassLayout.parseInstance(new Empty()).toPrintable());
    }
}
