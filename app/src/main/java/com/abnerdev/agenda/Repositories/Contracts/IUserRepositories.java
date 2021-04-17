package com.abnerdev.agenda.Repositories.Contracts;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;

import java.util.UUID;

public interface IUserRepositories {
    public void Create(User user);

    public boolean Exist(String email, String password);

    public User FindByUser();

    public void CreateContact( Contact contact);

    public void UpdateContact(Contact contact);

    public Contact FindByContact(String uuid_contact);

    public int CountContact();

}
