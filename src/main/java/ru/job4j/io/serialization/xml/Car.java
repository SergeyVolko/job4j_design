package ru.job4j.io.serialization.xml;

import ru.job4j.io.serialization.json.Certificate;

import java.io.StringReader;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    Certificate certificate;
    @XmlAttribute
    String stamp;

    @XmlAttribute
    boolean limitations;

    @XmlAttribute
    double power;

    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    String[] owners;

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

    public static void main(String[] args) throws Exception {
        final Car car = new Car(new Certificate("Ivanov", 1234),
                "Volvo", false,
                100, new String[] {"Ivanov", "Petrov", "Sidorov"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
