package ru.job4j.io.serialization.xml;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.io.serialization.json.Certificate;

import java.util.Arrays;

public class Car {
    Certificate certificate;
    String stamp;
    boolean limitations;
    double power;
    String[] owners;

    public Car(Certificate certificate, String stamp, boolean limitations, double power, String[] owners) {
        this.certificate = certificate;
        this.stamp = stamp;
        this.limitations = limitations;
        this.power = power;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "certificate=" + certificate
                + ", stamp='" + stamp + '\''
                + ", limitations=" + limitations
                + ", power=" + power
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }

    public static void main(String[] args) {
        final Car car = new Car(new Certificate("Ivanov", 1234),
                "Volvo", false,
                100, new String[] {"Ivanov", "Petrov", "Sidorov"});
    }
}
