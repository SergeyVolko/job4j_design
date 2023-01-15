package ru.job4j.ood.isp.prime3;

public class PdfReport implements Report {
    @Override
    public String generateExcel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String generatePdf() {
        return "Pdf report";
    }
}
