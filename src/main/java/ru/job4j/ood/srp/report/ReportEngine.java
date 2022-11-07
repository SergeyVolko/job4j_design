package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.scheme.SchemeReport;
import ru.job4j.ood.srp.store.OrderStore;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private final SchemeReport<Employee> schemeReport;
    private final OrderStore<Employee> orderStore;

    public ReportEngine(SchemeReport<Employee> schemeReport, OrderStore<Employee> orderStore) {
        this.schemeReport = schemeReport;
        this.orderStore = orderStore;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = schemeReport.getHeader();
        for (Employee employee : orderStore.getListStore(filter)) {
            schemeReport.getRowOfReport(text, employee);
        }
        return text.toString();
    }
}
