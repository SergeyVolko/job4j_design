package ru.job4j.ood.lsp.contolqu;

import java.util.Date;

public class LocalDateTimeExpirationCalculator implements ExpirationCalculator<Date> {
    @Override
    public double calculate(Date begin, Date end, Date add) {
        long addDateMs = add.getTime();
        long create = begin.getTime();
        long expire = end.getTime();
        if (addDateMs < create) {
            throw new IllegalArgumentException("Invalid date of addition.");
        }
        return (double) (addDateMs - create) / (expire - create) * 100;
    }
}
