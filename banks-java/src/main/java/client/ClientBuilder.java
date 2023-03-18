package client;

public class ClientBuilder {
    private String Id;
    private String Name;
    private String Surname;
    private String Passport;


    public Client GetClient() {
        return new Client(Id, Name, Surname, Passport);
    }

    public ClientBuilder setPassport(String passport) {
        Passport = passport;
        return this;
    }

    public ClientBuilder setId(String id) {
        Id = id;
        return this;
    }

    public ClientBuilder setName(String name) {
        Name = name;
        return this;
    }

    public ClientBuilder setSurname(String surname) {
        Surname = surname;
        return this;
    }
}
