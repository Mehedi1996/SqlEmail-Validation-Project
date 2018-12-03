package com.example.mehed.sqlemailvalidation.models;

public class PersonDetails {

    private String name,email,username, password;

    public PersonDetails(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
