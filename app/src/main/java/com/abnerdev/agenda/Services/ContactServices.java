package com.abnerdev.agenda.Services;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.Contrats.IContactServices;

public class ContactServices implements IContactServices {

    private UserRepositories userRepositories;
    private static ContactServices instance = new ContactServices();

    public static ContactServices getInstance(){
        return instance;
    }
    private ContactServices() {
        this.userRepositories = UserRepositories.getInstance();
    }

    @Override
    public boolean Create(Contact contact) {

        try{
        userRepositories.CreateContact(contact);
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    @Override
    public boolean Update(Contact contact) {
        try{
            userRepositories.UpdateContact(contact);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Contact FindById(String Id_contact) {
        return userRepositories.FindByContact(Id_contact);
    }

    @Override
    public int CountContact() {
        return UserRepositories.getInstance().CountContact();
    }
}
