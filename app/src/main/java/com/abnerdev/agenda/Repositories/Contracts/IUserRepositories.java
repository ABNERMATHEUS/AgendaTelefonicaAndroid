package com.abnerdev.agenda.Repositories.Contracts;

import com.abnerdev.agenda.Model.User;

import java.util.UUID;

public interface IUserRepositories {
    public void Create(User user);

    public String Exist(String email, String password);

    public User FindByUser(String uuid);

}
