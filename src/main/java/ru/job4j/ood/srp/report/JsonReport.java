package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private Store store;
    private Gson gson;

    public JsonReport(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
