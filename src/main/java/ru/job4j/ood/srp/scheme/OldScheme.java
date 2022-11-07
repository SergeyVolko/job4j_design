package ru.job4j.ood.srp.scheme;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;

public class OldScheme implements SchemeReport<Employee> {

    private final DateTimeParser<Calendar> dateTimeParser;
    private final String header;

    public OldScheme(DateTimeParser<Calendar> dateTimeParser, String header) {
        this.dateTimeParser = dateTimeParser;
        this.header = header;
    }
    @Override
    public StringBuilder getHeader() {
        return new StringBuilder(header).append(System.lineSeparator());
    }

    @Override
    public void getRowOfReport(StringBuilder content, Employee employee) {
        content.append(employee.getName()).append(" ")
                .append(dateTimeParser.parse(employee.getHired())).append(" ")
                .append(dateTimeParser.parse(employee.getFired())).append(" ")
                .append(employee.getSalary())
                .append(System.lineSeparator());
    }
}
