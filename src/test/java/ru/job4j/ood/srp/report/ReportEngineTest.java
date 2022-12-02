package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        String delimiter = " ";
        String headDelimiter = ";";
        Report engine = new ReportEngine(store, parser, delimiter, headDelimiter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
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
        store.add(worker);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        String delimiter = " ";
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Report engine = new AccountingEngine(store, parser, Currency.USD,
                converter, delimiter);
        StringBuilder expect = new StringBuilder()
                .append("Name Hired Fired Salary")
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
        Employee worker2 = new Employee("Igor", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        String delimiter = " ";
        Report engine = new HrEngine(store, delimiter);
        StringBuilder expect = new StringBuilder()
                .append("Name Salary")
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
        String delimiter = ";";
        Report engine = new CSVEngine(store, parser, delimiter);
        StringBuilder expect = new StringBuilder()
                .append("name;hired;fired;salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Igor", now, now, 200);
        store.add(worker1);
        store.add(worker2);
        XMLReport engine = new XMLReport(store);
        String format = """
                       <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                       <employees>
                           <employees>
                               <name>Ivan</name>
                               <hired>%s</hired>
                               <fired>%s</fired>
                               <salary>100.0</salary>
                           </employees>
                           <employees>
                               <name>Igor</name>
                               <hired>%s</hired>
                               <fired>%s</fired>
                               <salary>200.0</salary>
                           </employees>
                       </employees>
                       """;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault());
        String expect =
                String.format(format, dateFormat.format(now.getTime()),
                        dateFormat.format(now.getTime()),
                        dateFormat.format(now.getTime()),
                        dateFormat.format(now.getTime()));
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }

    @Test
    public void whenJsonGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        JsonReport jsonReport = new JsonReport(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"Ivan\",\"hired\":{\"year\":")
                .append(now.get(Calendar.YEAR)).append(",\"month\":")
                .append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"fired\":{\"year\":")
                .append(now.get(Calendar.YEAR)).append(",\"month\":")
                .append(now.get(Calendar.MONTH))
                .append(",\"dayOfMonth\":").append(now.get(Calendar.DAY_OF_MONTH))
                .append(",\"hourOfDay\":").append(now.get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":").append(now.get(Calendar.MINUTE))
                .append(",\"second\":").append(now.get(Calendar.SECOND))
                .append("},\"salary\":100.0}]");
        assertThat(jsonReport.generate(em -> true)).isEqualTo(expect.toString());
    }
}