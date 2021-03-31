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

    public void UpdateContact(Contact c){

        for(Contact contact: contacts){
            if(contact.getUuid().equals(c.getUuid())){
                contacts.remove(contact);
                contacts.add(c);
                break;
            }
        }

    }

    public ArrayList<Contact> getContato() {
        return contacts;
    }

    public void setContato(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
