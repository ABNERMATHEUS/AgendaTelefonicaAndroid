package com.abnerdev.agenda.Utils;

import android.content.Context;
import android.content.Intent;



public  class Utils {


    public static void Route_Start(Context packageContext, Class<?> cls,String ID_USER){
        Intent intent = new Intent(packageContext,cls);
        intent.putExtra("ID_USER",ID_USER == null?"":ID_USER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        packageContext.startActivity(intent);
    }

}
