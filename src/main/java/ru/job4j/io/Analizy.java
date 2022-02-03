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
                if ("".equals(first) && ("400".equals(status) || "500".equals(status))) {
                    first = str.substring(4);
                }
                if (!"".equals(first) && ("200".equals(status) || "300".equals(status))) {
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
