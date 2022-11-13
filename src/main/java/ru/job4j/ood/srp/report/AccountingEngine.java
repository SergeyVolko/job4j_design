package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class AccountingEngine implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency currency;
    private final CurrencyConverter converter;
    private String header;
    private String delimiter;

    public AccountingEngine(Store store, DateTimeParser<Calendar> dateTimeParser,
                            Currency currency, CurrencyConverter converter,
                            String header, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currency = currency;
        this.converter = converter;
        this.header = header;
        this.delimiter = delimiter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(header)
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(delimiter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimiter)
                    .append(roundTwoDigits(converter.convert(Currency.RUB, employee.getSalary(), currency)))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    private double roundTwoDigits(double num) {
        return Math.ceil(num * 100) / 100;
    }
}
