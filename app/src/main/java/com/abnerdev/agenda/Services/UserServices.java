package com.abnerdev.agenda.Services;

import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.Contrats.IUserServices;


public class UserServices  implements IUserServices {

    private UserRepositories userRepositories;

    public UserServices() {
        userRepositories = UserRepositories.getInstance();
    }

    @Override
    public String autentication(String email, String password) {
        return userRepositories.Exist(email,password);
    }

    @Override
    public void create(User user) {
        userRepositories.Create(user);
    }

    @Override
    public User FindById(String uuid) {
        return userRepositories.FindByUser(uuid);
    }


}
