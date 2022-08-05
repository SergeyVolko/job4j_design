package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    private static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";
    private static final String SEPARATOR = System.lineSeparator();
    private static final int COUNT = 50;
    private final UserGenerator userGenerator;
    private final Random random;
    private List<Comment> comments;
    private List<String> phrases;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(PATH_PHRASES);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public  List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct().limit(3).forEach(ints::add);
        for (int i = 0; i < COUNT; i++) {
            String comment = String.format("%s%s%s%s%s", phrases.get(ints.get(0)),
                    SEPARATOR, phrases.get(ints.get(1)), SEPARATOR,
                    phrases.get(ints.get(2)));
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}