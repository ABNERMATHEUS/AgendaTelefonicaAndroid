package com.abnerdev.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abnerdev.agenda.ArrayAdapter.ClickListener;
import com.abnerdev.agenda.ArrayAdapter.ContactArrayAdapter;
import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.ContactServices;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

import java.util.ArrayList;

public class ListContact extends AppCompatActivity {


    String Id_user;
    RecyclerView listView;
    ContactArrayAdapter contactArrayAdapter;
    ArrayList<String> contactsString;
    ArrayList<Contact>  contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        Id_user = UserRepositories.getInstance().getID_USER();
        listView = findViewById(R.id.recycleView);
        contactArrayAdapter = new ContactArrayAdapter();
        listView.setAdapter(contactArrayAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ListContact.this,DividerItemDecoration.VERTICAL);
        listView.addItemDecoration(itemDecoration);
        listView.setOnClickListener( new ClickListener());


    }

    public void AddContact(View view){
        /*ContactServices contactServices = new ContactServices();
        contactServices.Create(UserRepositories.getInstance().getID_USER(),
                new Contact("aa","419999","4444444","Casa"));*/
        Utils.Route_Start(ListContact.this,Contato.class);
        contactArrayAdapter.notifyDataSetChanged(); // Notificar, para atualizar a lista.

    }

    @Override
    protected void onResume() {
        super.onResume();
        contactArrayAdapter.notifyDataSetChanged();
    }

    /*void loadList(){
        UserServices userServices = new UserServices();
        contacts = userServices.FindById(Id_user).getPhoneBook().getContato();
        contactsString = new ArrayList<String>();

        for(Contact c:contacts){
            contactsString.add(c.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ListContact.this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                contactsString
        );



    }*/


}