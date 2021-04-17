package com.abnerdev.agenda.Services;

import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.Contrats.IUserServices;


public class UserServices  implements IUserServices {

    private UserRepositories userRepositories;
    private static UserServices instance = new UserServices();

    public static  UserServices getInstance(){
        return instance;
    }
    private UserServices() {
        userRepositories = UserRepositories.getInstance();
    }

    @Override
    public boolean autentication(String email, String password) {
        return  userRepositories.Exist(email,password);
    }

    @Override
    public void create(User user) {
        userRepositories.Create(user);
    }

    @Override
    public User FindById() {
        return userRepositories.FindByUser();
    }


}
