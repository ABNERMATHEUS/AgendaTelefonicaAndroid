package com.abnerdev.agenda.DataContext;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abnerdev.agenda.Model.PhoneBook;

public class DataContext extends SQLiteOpenHelper {


    private static DataContext Instance;

    private static final String DATABASE_NAME = "app";
    public static final String TABELA_USUARIOS = "Users";
    public static final String TABLEA_CONTATOS = "Contacts";
    public static final int DATABASE_VERSION = 1;

    //TABLE USER
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    //TABLE CONTACTS
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PHONE = "phone";
    public static final String COL_TypeCONTACT = "typeContact";
    public static final String COL_ID_USER = "idUser";
    public static final String COL_IMAGE = "image";

    private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS Users(id TEXT PRIMARY KEY , email VARCHAR, password VARCHAR)";

    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE IF NOT EXISTS Contacts(id TEXT PRIMARY KEY , name VARCHAR, address VARCHAR, phone VARCHAR, typeContact int(3), image VARCHAR, idUser VARCHAR)";



    public static DataContext getInstance(Context context) {

        if (Instance == null) {
            Instance = new DataContext(context.getApplicationContext());
        }

        return Instance;
    }


    private DataContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_USUARIOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLEA_CONTATOS);
        onCreate(db);
    }



}
