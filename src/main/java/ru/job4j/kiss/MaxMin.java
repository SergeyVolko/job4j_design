package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return getMaxMin(value, (v1, v2) -> comparator.compare(v1, v2) < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return getMaxMin(value, (v1, v2) -> comparator.compare(v1, v2) > 0);
    }

    private <T> T getMaxMin(List<T> value, BiPredicate<T, T> predicate) {
        T v = value.size() == 0 ? null : value.get(0);
        for (int i = 1; i < value.size(); i++) {
            if (predicate.test(v, value.get(i))) {
                v = value.get(i);
            }
        }
        return v;
    }
}
