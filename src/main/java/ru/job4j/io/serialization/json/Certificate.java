package ru.job4j.io.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "certificate")
@XmlAccessorType(XmlAccessType.FIELD)
public class Certificate {
    @XmlAttribute
    String owner;

    @XmlAttribute
    int id;

    public String getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public Certificate() {

    }
    public Certificate(String owner, int id) {
        this.owner = owner;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Certificate{"
                + "owner='" + owner + '\''
                + ", id=" + id
                + '}';
    }
}
