package com.abnerdev.agenda.Services;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.Contrats.IContactServices;

public class ContactServices implements IContactServices {

    private UserRepositories userRepositories;

    public ContactServices() {
        this.userRepositories = UserRepositories.getInstance();
    }

    @Override
    public boolean Create(String Id_user, Contact contact) {

        try{
        userRepositories.CreateContact(Id_user,contact);
        }catch (Exception e){
            return  false;
        }
        return true;
    }

    @Override
    public boolean Update(String Id_user,Contact contact) {
        try{
            userRepositories.UpdateContact(Id_user,contact);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Contact FindById(String Id_user, String Id_contact) {
        return userRepositories.FindByContact(Id_user,Id_contact);
    }
}
