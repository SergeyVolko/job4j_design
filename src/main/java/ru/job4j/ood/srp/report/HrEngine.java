package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HrEngine implements Report {
    private final Store store;
    private String delimiter;
    private static final Comparator<Employee> DESC_COMP
            = (o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary());
    public HrEngine(Store store, String delimiter) {
        this.store = store;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name").append(delimiter).append("Salary")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter);
        employees.sort(DESC_COMP);
        for (Employee employee : employees) {
            text.append(employee.getName()).append(delimiter)
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
