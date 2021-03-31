package com.abnerdev.agenda.Repositories;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.Contracts.IUserRepositories;

import java.util.ArrayList;
import java.util.UUID;

public class UserRepositories implements IUserRepositories {

    private static UserRepositories instance = new UserRepositories();

    private ArrayList<User> users = new ArrayList<User>();


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
    public String Exist(String email, String password) {
        for(User user: users){
            if(user.getEmail().equals(email) && user.getPassword().equals(password))
                return user.getUuid().toString();
        }
        return null;
    }

    @Override
    public User FindByUser(String uuid) {
        for(User user : users){
            if(user.getUuid().toString().equals(uuid))
                return user;
        }
        return null;
    }

    @Override
    public void CreateContact(String uuid, Contact contact) {
        User user = FindByUser(uuid);
        user.getPhoneBook().addContat(contact);
    }

    @Override
    public void UpdateContact(String uuid, Contact contact) {
        User user = FindByUser(uuid);
        user.getPhoneBook().UpdateContact(contact);
    }

    @Override
    public Contact FindByContact(String uuid, String uuid_contact) {
        User user = FindByUser(uuid);
        for(Contact contact: user.getPhoneBook().getContato()){
            if(contact.getUuid().equals(uuid_contact)){
                return contact;
            }
        }
        return null;
    }


}
