package com.abnerdev.agenda.Model;

import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<Contact>();
    }

    public void addContat(Contact contact){
        contacts.add(contact);
    }

    public ArrayList<Contact> getContato() {
        return contacts;
    }

    public void setContato(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
