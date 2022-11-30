package ru.job4j.io.serialization.xml.prime;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws JAXBException {
        var users = new ArrayList<>(List.of(
                new User("123", "456"),
                new User("abc", "xyz"),
                new User("job4j", "junior")
        ));
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Igor", now, now, 200);
        var employees = new ArrayList<>(List.of(
                worker1,
                worker2
        ));
        JAXBContext context = JAXBContext.newInstance(Store.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Store(employees), writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class User {
        private String name;
        private String lastName;

        public User(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    @XmlRootElement(name = "users")
    public static class Users {

        private List<User> users;

        public Users() {

        }

        public Users(List<User> users) {
            this.users = users;
        }

        public List<User> getUsers() {
            return users;
        }

        public void setUsers(List<User> users) {
            this.users = users;
        }
    }

    public static class Employee {
        private String name;
        private Calendar hired;
        private Calendar fired;
        private double salary;

        public Employee() {

        }

        public Employee(String name, Calendar hired, Calendar fired, double salary) {
            this.name = name;
            this.hired = hired;
            this.fired = fired;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Calendar getHired() {
            return hired;
        }

        public void setHired(Calendar hired) {
            this.hired = hired;
        }

        public Calendar getFired() {
            return fired;
        }

        public void setFired(Calendar fired) {
            this.fired = fired;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Employee employee = (Employee) o;
            return Objects.equals(name, employee.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    @XmlRootElement(name = "store")
    public static class Store {
        private List<Employee> employees;

        public Store() {

        }

        public Store(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getUsers() {
            return employees;
        }

        public void setUsers(List<Employee> employees) {
            this.employees = employees;
        }
    }
}
