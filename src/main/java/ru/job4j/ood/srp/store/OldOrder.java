package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public class OldOrder implements OrderStore<Employee> {

    private Store store;

    public OldOrder(Store store) {
        this.store = store;
    }

    @Override
    public List<Employee> getListStore(Predicate<Employee> filter) {
        return store.findBy(filter);
    }
}
