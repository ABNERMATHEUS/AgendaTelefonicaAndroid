package com.abnerdev.agenda.Repositories;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.Contracts.IUserRepositories;

import java.util.ArrayList;
import java.util.UUID;

public class UserRepositories implements IUserRepositories {

    private static UserRepositories instance = new UserRepositories();

    private String ID_USER;
    private ArrayList<User> users = new ArrayList<User>();

    public String getID_USER() {
        return ID_USER;
    }

    public void setID_USER(String ID_USER) {
        this.ID_USER = ID_USER;
    }

    public static  UserRepositories getInstance(){
        return instance;
    }

    private UserRepositories() {
        this.users = users;
    }

    @Override
    public void Create(User user) {
        users.add(user);
    }

    @Override
    public boolean Exist(String email, String password) {
        for(User user: users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                this.setID_USER(user.getUuid().toString());
                return true;
            }

        }

        return false;
    }

    @Override
    public User FindByUser() {
        for(User user : users){
            if(user.getUuid().toString().equals(this.getID_USER()))
                return user;
        }
        return null;
    }

    @Override
    public void CreateContact(Contact contact) {
        User user = FindByUser();
        user.getPhoneBook().addContat(contact);
    }

    @Override
    public void UpdateContact( Contact contact) {
        User user = FindByUser();
        user.getPhoneBook().UpdateContact(contact);
    }

    @Override
    public Contact FindByContact(String uuid_contact) {
        User user = FindByUser();
        for(Contact contact: user.getPhoneBook().getContato()){
            if(contact.getUuid().equals(uuid_contact)){
                return contact;
            }
        }
        return null;
    }

    @Override
    public int CountContact() {
        return FindByUser().getPhoneBook().getContato().size();
    }


}
