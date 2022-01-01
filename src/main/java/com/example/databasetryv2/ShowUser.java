package com.example.databasetryv2;

import javafx.beans.property.SimpleStringProperty;

public class ShowUser {
    private final SimpleStringProperty email;
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;

    public ShowUser(String email, String name, String address) {
        this.email = new SimpleStringProperty(email);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
    }

    public String getEmail() {
        return email.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }
}
