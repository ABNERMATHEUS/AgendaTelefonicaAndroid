package com.abnerdev.agenda.Repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.abnerdev.agenda.DataContext.DataContext;
import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.Contracts.IUserRepositories;

import java.util.ArrayList;

public class UserRepositoriesSQLite implements IUserRepositories {

    private DataContext dataContext;
    private static  UserRepositoriesSQLite instance;
    private Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    private String ID_USER;



    public static  UserRepositoriesSQLite getInstance(Context context){

        if(instance==null){
            return new UserRepositoriesSQLite(context);
        }
        instance.setContext(context);
        return instance;
    }

    private UserRepositoriesSQLite(Context context) {
        this.context = context;
        dataContext = DataContext.getInstance(context);
    }


    @Override
    public void Create(User user) {
        SQLiteDatabase database =  dataContext.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataContext.COL_ID,user.getUuid());
        contentValues.put(dataContext.COL_EMAIL,user.getEmail());
        contentValues.put(dataContext.COL_PASSWORD,user.getPassword());
        database.insert(dataContext.TABELA_USUARIOS,null,contentValues);
        database.close();
    }

    @Override
    public boolean Exist(String email, String password) {
        SQLiteDatabase database =  dataContext.getReadableDatabase();
        String query  = "SELECT * FROM "+dataContext.TABELA_USUARIOS+" WHERE "+dataContext.COL_EMAIL+" = '"+email+"' AND "+dataContext.COL_PASSWORD+" = '"+password+"'";
        Cursor cursor = database.rawQuery(query,null);


        if(cursor.moveToFirst()){
            setID_USER(cursor.getString(cursor.getColumnIndex(dataContext.COL_ID)));
            database.close();
            return true;
        }else{
            database.close();
            return false;
        }


    }

    @Override
    public User FindByUser() {

        SQLiteDatabase database =  dataContext.getReadableDatabase();
        String queryUser  = "SELECT * FROM "+dataContext.TABELA_USUARIOS+" WHERE "+dataContext.COL_ID+" = '"+getID_USER()+"'";
        String queryContact  = "SELECT NAME FROM "+dataContext.TABLEA_CONTATOS+" WHERE "+dataContext.COL_ID_USER+" = '"+getID_USER()+"'";

        Cursor cursor = database.rawQuery(queryUser,null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setUuid(cursor.getString(cursor.getColumnIndex(dataContext.COL_ID)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(dataContext.COL_EMAIL)));
            user.setUuid(cursor.getString(cursor.getColumnIndex(dataContext.COL_ID)));
        }


        cursor = database.rawQuery(queryContact,null);
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setUuid(cursor.getString(cursor.getColumnIndex(dataContext.COL_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(dataContext.COL_NAME)));
                contact.setAddress(cursor.getString(cursor.getColumnIndex(dataContext.COL_ADDRESS)));
                contact.setPhone(cursor.getString(cursor.getColumnIndex(dataContext.COL_ADDRESS)));
                user.getPhoneBook().addContat(contact);
            }while (cursor.moveToNext());
        }
        database.close();
        return user;

    }

    @Override
    public void CreateContact(Contact contact) {

        SQLiteDatabase database =  dataContext.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataContext.COL_ID,contact.getUuid());
        contentValues.put(dataContext.COL_NAME,contact.getName());
        contentValues.put(dataContext.COL_ADDRESS,contact.getAddress());
        contentValues.put(dataContext.COL_PHONE,contact.getPhone());
        database.insert(dataContext.TABLEA_CONTATOS,null,contentValues);
        database.close();
    }

    @Override
    public void UpdateContact(Contact contact) {

        SQLiteDatabase database =  dataContext.getWritableDatabase();

        String updateQuery = "UPDATE "+dataContext.TABLEA_CONTATOS+" SET " +
                dataContext.COL_NAME+" = '"+contact.getName()+"'," +
                dataContext.COL_ADDRESS+" = '"+contact.getAddress()+"',"+
                dataContext.COL_PHONE+" = '"+contact.getPhone()+"',"+
                "WHERE "+dataContext.COL_ID_USER+" = '"+getID_USER()+"'";

        database.execSQL(updateQuery);
    }

    @Override
    public Contact FindByContact(String uuid_contact) {

        SQLiteDatabase database =  dataContext.getReadableDatabase();
        String queryContact  = "SELECT NAME FROM "+dataContext.TABLEA_CONTATOS+" WHERE "+dataContext.COL_ID_USER+" = '"+getID_USER()+"' AND "+dataContext.COL_ID+" = '"+uuid_contact+"'";
        Cursor cursor = database.rawQuery(queryContact,null);
        if(cursor.moveToFirst()){
            Contact contact = new Contact();
            contact.setUuid(cursor.getString(cursor.getColumnIndex(dataContext.COL_ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(dataContext.COL_NAME)));
            contact.setAddress(cursor.getString(cursor.getColumnIndex(dataContext.COL_ADDRESS)));
            contact.setPhone(cursor.getString(cursor.getColumnIndex(dataContext.COL_ADDRESS)));
            return contact;
        }

        return null;
    }

    @Override
    public int CountContact() {
        return FindByUser().getPhoneBook().getContato().size();
    }



    public String getID_USER() {
        return ID_USER;
    }

    public void setID_USER(String ID_USER) {
        this.ID_USER = ID_USER;
    }


}
