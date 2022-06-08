package ru.job4j.gc;

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + '}';
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20000; i++) {
            new User("Alex", i);
        }
    }
}
