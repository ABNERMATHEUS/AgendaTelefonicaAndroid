package com.abnerdev.agenda.Model;

import java.util.UUID;

public class Contact {

    private String uuid;
    private String Name;

    private String Address;

    private String Phone;

    private String Type;

    private String Image;

    public Contact(String name, String address, String phone,String type, String image) {
        Name = name;
        Address = address;
        Phone = phone;
        uuid = UUID.randomUUID().toString();
        Type = type;
        Image = image;
    }

    public Contact() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getImage() { return Image; }

    public void setImage(String image) { Image = image; }
}
