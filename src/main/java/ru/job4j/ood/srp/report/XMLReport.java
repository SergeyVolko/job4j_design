package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private Marshaller marshaller;
    private Employees employees;
    private Store store;

    public XMLReport(Store store) {
        this.store = store;
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class, Employee.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            employees = new Employees();
        } catch (JAXBException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        employees.setEmployees(store.findBy(filter));
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(employees, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @XmlRootElement(name = "employees")
    private static class Employees {
        private List<Employee> employees;

        public Employees() {

        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
