package com.abnerdev.agenda.Services;

import android.content.Context;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Repositories.UserRepositoriesSQLite;
import com.abnerdev.agenda.Services.Contrats.IContactServices;

public class ContactServices implements IContactServices {

    private UserRepositoriesSQLite userRepositories;
    private static ContactServices instance;

    public static ContactServices getInstance(Context context)
    {

        if(instance == null){
            instance = new ContactServices(context);
        }
        instance.userRepositories.setContext(context);
        return instance;
    }
    private ContactServices(Context context) {

        this.userRepositories = UserRepositoriesSQLite.getInstance(context);
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
        return userRepositories.CountContact();
    }
}
