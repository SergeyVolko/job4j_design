package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    private final String pathName = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private final String pathSurnames = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private final String pathPatrons = "src/main/java/ru/job4j/gc/leak/files/patr.txt";
    private final String separator = " ";
    private final Integer newUsers = 1000;

    private List<User> users;
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
    }

    @Override
    public void generate() {
        users = new ArrayList<>();
        List<String> names = readAll(pathName);
        List<String> surnames = readAll(pathSurnames);
        List<String> patrons = readAll(pathPatrons);
        for (int i = 0; i < newUsers; i++) {
            users.add(new User(
                    String.format("%s%s%s%s", surnames.get(random.nextInt(surnames.size())),
                            separator, names.get(random.nextInt(names.size())), separator,
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