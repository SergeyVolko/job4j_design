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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children
                && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
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
        System.out.println("first == second?:" + first.equals(second));
        Map<User, Object> map = new HashMap<>();
        Object firstObj = new Object();
        Object secondObj = new Object();
        System.out.println("firstObj=" + firstObj + " secondObj=" + secondObj);
        map.put(first, firstObj);
        map.put(second, secondObj);
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
