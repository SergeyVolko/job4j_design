package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlElement
    Certificate certificate;

    @XmlElement
    String stamp;

    @XmlElement
    boolean limitations;

    @XmlElement
    double power;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    String[] owners;

    public Certificate getCertificate() {
        return certificate;
    }

    public String getStamp() {
        return stamp;
    }

    public boolean isLimitations() {
        return limitations;
    }

    public double getPower() {
        return power;
    }

    public String[] getOwners() {
        return owners;
    }

    public Car() {

    }

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
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String carJson =
                "{"
                        + "\"certificate\":"
                        + "{"
                        + "\"owner\":\"Ivan Petrovich\","
                        + "\"id\": 8888"
                        + "},"
                        + "\"stamp\":\"Lada\","
                        + "\"limitations\":false,"
                        + "\"power\":80.5,"
                        + "\"owners\":"
                        + "[\"Petrov\",\"Ivanov\"]"
                        + "}";
        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
        System.out.println("------------------------------------------------");
        jsonInPojo();
    }

    public static void jsonInPojo() {
        /* JSONObject из json-строки строки */
        JSONObject jsonCertificate = new JSONObject("{\"owner\":\"Ivanov\",\"id\":1234}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Petrov");
        list.add("Ivanov");
        list.add("Sidorov");
        JSONArray jsonOwners = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car car = new Car(new Certificate("Ivanov", 1234),
                "Volvo", false,
                100, new String[] {"Ivanov", "Petrov", "Sidorov"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("certificate", car.getCertificate());
        jsonObject.put("stamp", car.getStamp());
        jsonObject.put("limitations", car.isLimitations());
        jsonObject.put("certificate", jsonCertificate);
        jsonObject.put("owners", jsonOwners);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
