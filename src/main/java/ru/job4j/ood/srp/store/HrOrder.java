package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;
import java.util.function.Predicate;

public class HrOrder implements OrderStore<Employee> {

    private Store store;

    public HrOrder(Store store) {
        this.store = store;
    }

    @Override
    public List<Employee> getListStore(Predicate<Employee> filter) {
        List<Employee> res = store.findBy(filter);
        res.sort((o1, o2) -> (int) Math.round(o2.getSalary() - o1.getSalary()));
        return res;
    }
}
