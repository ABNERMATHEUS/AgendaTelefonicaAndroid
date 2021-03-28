package com.abnerdev.agenda.Services.Contrats;

import com.abnerdev.agenda.Model.User;

import java.util.UUID;

public interface IUserServices {

    public String autentication(String email, String password);

    public void create(User user);

    public User FindById(String uuid);


}
