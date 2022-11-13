package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class HrEngine implements Report {
    private final Store store;
    private String header;
    private String delimiter;

    public HrEngine(Store store, String header, String delimiter) {
        this.store = store;
        this.header = header;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(header)
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort((o1, o2) -> (int) Math.round(o2.getSalary() - o1.getSalary()));
        for (Employee employee : employees) {
            text.append(employee.getName()).append(delimiter)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
