package com.abnerdev.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

import java.util.ArrayList;

public class ListContact extends AppCompatActivity {


    String Id_user;
    ListView listView;
    ArrayList<String> contactsString;
    ArrayList<Contact>  contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        Id_user = getIntent().getStringExtra("ID_USER");
        listView = findViewById(R.id.listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setSelector(android.R.color.darker_gray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Id_contact = contacts.get(position).getUuid();
                Intent intent = new Intent(ListContact.this,Contato.class);
                intent.putExtra("ID_USER",Id_user).putExtra("ID_CONTACT",Id_contact);
                startActivity(intent);
            }
        });
        loadList();
    }

    public void AddContact(View view){
        Utils.Route_Start(ListContact.this,Contato.class,Id_user);
    }

    void loadList(){
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

        listView.setAdapter(adapter);

    }


}