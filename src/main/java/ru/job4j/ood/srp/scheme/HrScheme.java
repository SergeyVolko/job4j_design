package ru.job4j.ood.srp.scheme;

import ru.job4j.ood.srp.model.Employee;

public class HrScheme implements SchemeReport<Employee> {

    private String header;

    public HrScheme(String header) {
        this.header = header;
    }

    @Override
    public StringBuilder getHeader() {
        return new StringBuilder(header).append(System.lineSeparator());
    }

    @Override
    public void getRowOfReport(StringBuilder content, Employee employee) {
        content.append(employee.getName()).append(" ")
                .append(employee.getSalary())
                .append(System.lineSeparator());
    }
}
