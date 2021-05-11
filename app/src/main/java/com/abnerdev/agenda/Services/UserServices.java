package com.abnerdev.agenda.Services;

import android.content.Context;

import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Repositories.UserRepositoriesSQLite;
import com.abnerdev.agenda.Services.Contrats.IUserServices;


public class UserServices  implements IUserServices {

    private UserRepositoriesSQLite userRepositories;
    private static UserServices instance ;

    public static  UserServices getInstance(Context context){
        if(instance ==null){
            instance = new UserServices(context);
        }
        else if(!instance.userRepositories.getContext().equals(context)){
            instance.userRepositories.setContext(context);
        }

        return instance;
    }
    private UserServices(Context context) {
        userRepositories = UserRepositoriesSQLite.getInstance(context);
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
