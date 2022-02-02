package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String first = "";
            String last = "";
            while (in.ready()) {
                String str = in.readLine();
                String status = str.substring(0, 3);
                if (first.equals("") && (status.equals("400") || status.equals("500"))) {
                    first = str.substring(4);
                }
                if (!first.equals("") && (status.equals("200") || status.equals("300"))) {
                    last = str.substring(4);
                    stringJoiner.add(first + ";" + last + ";");
                    first = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(stringJoiner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "target.csv");
    }
}
