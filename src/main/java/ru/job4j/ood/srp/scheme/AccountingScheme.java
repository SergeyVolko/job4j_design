package ru.job4j.ood.srp.scheme;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;

public class AccountingScheme implements SchemeReport<Employee> {

    private String header;
    private Currency currency;
    private DateTimeParser<Calendar> dateTimeParser;
    private CurrencyConverter converter;

    public AccountingScheme(DateTimeParser<Calendar> dateTimeParser,
                            Currency currency, String header,
                            CurrencyConverter converter) {
        this.dateTimeParser = dateTimeParser;
        this.currency = currency;
        this.header = header;
        this.converter = converter;
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
                .append(roundTwoDigits(converter.convert(Currency.RUB, employee.getSalary(), currency)))
                .append(System.lineSeparator());
    }

    private double roundTwoDigits(double num) {
        return Math.ceil(num * 100) / 100;
    }
}
