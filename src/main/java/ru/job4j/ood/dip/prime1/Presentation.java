package ru.job4j.ood.dip.prime1;

public class Presentation {
    private ExcelData data;

    public Presentation(ExcelData data) {
        this.data = data;
    }

    public void setData(ExcelData data) {
        this.data = data;
    }

    public ExcelData getData() {
        return new ExcelData();
    }
}
