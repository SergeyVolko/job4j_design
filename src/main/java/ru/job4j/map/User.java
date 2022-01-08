package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.getTime()
                + '}';
    }

    public static void main(String[] args) {
        User first = new User("Ivan", 36,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        User second = new User("Ivan", 36,
                new GregorianCalendar(1986, Calendar.MAY, 14));
        System.out.println("heshcode first = " + first.hashCode()
                + " heshcode second = " + second.hashCode());
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
