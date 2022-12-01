package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonEngine implements Report {
    private List<Employee> store;
    private Gson gson;

    public JsonEngine(List<Employee> store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store);
    }
}
