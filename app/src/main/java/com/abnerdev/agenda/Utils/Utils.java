package com.abnerdev.agenda.Utils;

import android.content.Context;
import android.content.Intent;

import com.abnerdev.agenda.Repositories.UserRepositories;


public  class Utils {


    public static void Route_Start(Context packageContext, Class<?> cls){
        Intent intent = new Intent(packageContext,cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        packageContext.startActivity(intent);
    }

}
