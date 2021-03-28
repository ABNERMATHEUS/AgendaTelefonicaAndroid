package com.abnerdev.agenda.Model;

import java.util.UUID;

public class User {

    private String email;
    private String password;
    private PhoneBook phoneBook;
    private UUID uuid;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.phoneBook = new PhoneBook();
        uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public void setPhoneBook(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

}
