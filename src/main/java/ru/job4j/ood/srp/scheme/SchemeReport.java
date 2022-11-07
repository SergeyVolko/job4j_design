package ru.job4j.ood.srp.scheme;

public interface SchemeReport<T> {
    StringBuilder getHeader();
    void getRowOfReport(StringBuilder content, T t);
}
