package ru.job4j.io.serialization.json;

public class Certificate {
    String owner;
    int id;

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
