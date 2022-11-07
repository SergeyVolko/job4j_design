package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.scheme.AccountingScheme;
import ru.job4j.ood.srp.scheme.CsvScheme;
import ru.job4j.ood.srp.scheme.HrScheme;
import ru.job4j.ood.srp.scheme.OldScheme;
import ru.job4j.ood.srp.store.HrOrder;
import ru.job4j.ood.srp.store.MemStore;
import ru.job4j.ood.srp.store.OldOrder;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        OldOrder oldOrder = new OldOrder(store);
        String header = "Name; Hired; Fired; Salary;";
        OldScheme oldScheme = new OldScheme(parser, header);
        Report engine = new ReportEngine(oldScheme, oldOrder);
        StringBuilder expect = new StringBuilder()
                .append(header)
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        String header = "Name; Hired; Fired; Salary;";
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        OldOrder oldOrder = new OldOrder(store);
        AccountingScheme scheme = new AccountingScheme(parser, Currency.USD, header, converter);
        Report engine = new ReportEngine(scheme, oldOrder);
        StringBuilder expect = new StringBuilder()
                .append(header)
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append("1.62")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Sidr", now, now, 200);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        HrOrder hrOrder = new HrOrder(store);
        String header = "Name; Salary;";
        HrScheme hrScheme = new HrScheme(header);
        Report engine = new ReportEngine(hrScheme, hrOrder);
        StringBuilder expect = new StringBuilder()
                .append(header)
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenCSVGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        OldOrder oldOrder = new OldOrder(store);
        String header = "Name;Hired;Fired;Salary";
        CsvScheme scheme = new CsvScheme(parser, header);
        Report engine = new ReportEngine(scheme, oldOrder);
        StringBuilder expect = new StringBuilder()
                .append(header)
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}