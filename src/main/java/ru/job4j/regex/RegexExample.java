package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        /**
         * Pattern pattern = Pattern.compile("Я учусь на Job4j");
         *
         * String text1 = "Я учусь на Job4j";
         * Matcher matcher1 = pattern.matcher(text1);
         * boolean isPresent1 = matcher1.matches();
         * System.out.println(isPresent1);
         *
         * String text2 = "Я учусь на курсе Job4j";
         * Matcher matcher2 = pattern.matcher(text2);
         * boolean isPresent2 = matcher2.matches();
         * System.out.println(isPresent2);
         */

        /**
         * Pattern pattern = Pattern.compile("Job4j");
         *
         * String text1 = "Job4j";
         * Matcher matcher1 = pattern.matcher(text1);
         * boolean isPresent1 = matcher1.matches();
         * System.out.println(isPresent1);
         *
         * String text2 = "job4j";
         * Matcher matcher2 = pattern.matcher(text2);
         * boolean isPresent2 = matcher2.matches();
         * System.out.println(isPresent2);
         */

        /**
         *         Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
         *
         *         String text1 = "Job4j";
         *         Matcher matcher1 = pattern.matcher(text1);
         *         boolean isPresent1 = matcher1.matches();
         *         System.out.println(isPresent1);
         *
         *         String text2 = "joB4J";
         *         Matcher matcher2 = pattern.matcher(text2);
         *         boolean isPresent2 = matcher2.matches();
         *         System.out.println(isPresent2);
         */

        /**
         *         Pattern pattern = Pattern.compile("Job4j");
         *         String text = "Я учусь на курсе Job4j";
         *         Matcher matcher = pattern.matcher(text);
         *         boolean isPresent = matcher.find();
         *         System.out.println(isPresent);
         */

        /**
         *         Pattern pattern = Pattern.compile("Job4j");
         *         String text = "Job4j и Job4j и Job4j";
         *         Matcher matcher = pattern.matcher(text);
         *         while (matcher.find()) {
         *             System.out.println("Найдено совпадение");
         *         }
         */

        /**
         *         Pattern pattern = Pattern.compile("Job4j");
         *         String text = "Job4j1 и Job4j2 и Job4j3";
         *         Matcher matcher = pattern.matcher(text);
         *         while (matcher.find()) {
         *             System.out.println("Найдено совпадение: " + matcher.group());
         *         }
         */

        /**
         *         Pattern pattern = Pattern.compile("Job4j");
         *         String text = "Job4j1 и Job4j2 и Job4j3";
         *         Matcher matcher = pattern.matcher(text);
         *         while (matcher.find()) {
         *             System.out.println("Найдено совпадение. iStart: " + matcher.start()
         *                     + " iEnd: " + matcher.end());
         *         }
         */

        Pattern pattern = Pattern.compile("123");
        String text = "1231 и 1232 и 1233";
        Matcher matcher = pattern.matcher(text);
        String rsl = matcher.replaceAll("Job4j");
        System.out.println(rsl);
    }
}
