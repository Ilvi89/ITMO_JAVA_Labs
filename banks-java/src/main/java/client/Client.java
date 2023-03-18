package client;

import java.util.Objects;

public class Client {

    private final String id;
    private String name;
    private String surname;
    private String passport;

    public Client(String id, String name, String surname, String passport) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.passport = passport;
    }

    public Client(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public static ClientBuilder Builder(String id, String name, String surname) {
        return new ClientBuilder().setId(id).setName(name).setSurname(surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }


}
