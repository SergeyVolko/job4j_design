package ru.job4j.io.csvreader;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CSVReader {
    private  File source;
    private  File target;

    public void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(source).useDelimiter(System.lineSeparator())) {
            Set<String> filters = Arrays.stream(argsName.get("filter").split(","))
                    .collect(Collectors.toSet());
            if ("stdout".equals(out)) {
                show(scanner, filters, System.out::print, delimiter);
            } else {
                try (PrintWriter printWriter = new PrintWriter(new FileWriter(target.getAbsolutePath(), true))) {
                    show(scanner, filters, printWriter::print, delimiter);
                }
            }

        }
    }

    private void show(Scanner scanner, Set<String> filters, Consumer<String> consumer, String delimiter) {
        String[] strings;
        Set<Integer> positions = getPositions(filters, scanner, consumer, delimiter);
        int pos = 0;
        String el;
        while (scanner.hasNext()) {
            strings = scanner.next().split(delimiter);
            pos = 0;
            for (int i = 0; i < strings.length; i++) {
                if (positions.contains(i)) {
                    el = pos == positions.size() - 1 ? strings[i].concat(System.lineSeparator())
                            : strings[i].concat(";");
                    consumer.accept(el);
                    pos++;
                }
            }
        }
    }

    private Set<Integer> getPositions(Set<String> filters, Scanner scanner, Consumer<String> consumer, String delimiter) {
        Set<Integer> positions = new HashSet<>();
        int pos = 0;
        int size = filters.size();
        int count = 0;
        String prStr;
        if (scanner.hasNext()) {
            String[] strings = scanner.next().split(delimiter);
            for (String s : strings) {
                if (filters.contains(s)) {
                    positions.add(pos);
                    prStr = count == size - 1 ? s.concat(System.lineSeparator()) : s.concat(delimiter);
                    consumer.accept(prStr);
                    count++;
                }
                pos++;
                if (count == filters.size()) {
                    break;
                }
            }
        }
        return positions;
    }
    public void validate(ArgsName argsName) {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        if (path == null) {
            throw new IllegalArgumentException("Missing parameter path");
        }
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("Probably an extension. Must be csv");
        }
        if (delimiter == null) {
            throw new IllegalArgumentException("Missing parameter delimiter");
        }
        if (out == null) {
            throw new IllegalArgumentException("Missing parameter out");
        }
        if (!out.endsWith(".csv") && !"stdout".equals(out)) {
            throw new IllegalArgumentException("Probably an extension. Must be csv or stdout.");
        }
        if (filter == null) {
            throw new IllegalArgumentException("Missing parameter filter");
        }
        source = new File(argsName.get("path"));
        if (!source.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", source.getAbsoluteFile()));
        }
        if (!"stdout".equals(out)) {
            target = new File(out);
            if (!target.exists()) {
                throw new IllegalArgumentException(String.format("Not exist %s", target.getAbsoluteFile()));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        CSVReader csvReader = new CSVReader();
        csvReader.handle(ArgsName.of(args));
    }
}
