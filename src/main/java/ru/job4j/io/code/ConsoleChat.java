package ru.job4j.io.code;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            List<String> phrases = readPhrases();
            List<String> log = new ArrayList<>();
            String fraze = br.readLine();
            String botAnswer;
            boolean isStop = STOP.equals(fraze);
            while (!OUT.equals(fraze)) {
                log.add(fraze);
                if (STOP.equals(fraze)) {
                    isStop = true;
                } else if (CONTINUE.equals(fraze) && isStop) {
                    isStop = false;
                }
                if (!isStop) {
                    botAnswer = phrases.get((int) (Math.random() * phrases.size()));
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                }
                fraze = br.readLine();
            }
            saveLog(log);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> readPhrases() {
        List<String> result = null;
        try (BufferedReader br = new BufferedReader(
                new FileReader(botAnswers))) {
             result = br.lines().collect(Collectors.toList());
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src/main/java/ru/job4j/io/code/log.txt",
                "src/main/java/ru/job4j/io/code/answers.txt");
        cc.run();
    }
}
