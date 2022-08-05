package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    private static final String PATH_NAME = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
    private static final String SEPARATOR = " ";
    private static final int NEW_USERS = 1000;

    private List<User> users;
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
    }

    @Override
    public void generate() {
        users = new ArrayList<>();
        List<String> names = readAll(PATH_NAME);
        List<String> surnames = readAll(PATH_SURNAMES);
        List<String> patrons = readAll(PATH_PATRONS);
        for (int i = 0; i < NEW_USERS; i++) {
            users.add(new User(
                    String.format("%s%s%s%s", surnames.get(random.nextInt(surnames.size())),
                            SEPARATOR, names.get(random.nextInt(names.size())), SEPARATOR,
                            patrons.get(random.nextInt(patrons.size())))));
        }
    }

    private List<String> readAll(String path) {
        List<String> initial;
        try {
            initial = read(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return initial;
    }

    public User randomUser() {
        User user = null;
        if (users != null) {
            user = users.get(random.nextInt(users.size()));
        }
        return user;
    }

    public List<User> getUsers() {
        return users;
    }
}