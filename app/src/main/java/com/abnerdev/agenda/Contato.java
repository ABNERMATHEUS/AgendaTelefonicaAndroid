package com.abnerdev.agenda;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Services.ContactServices;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

public class Contato extends AppCompatActivity {


    EditText name;
    EditText address;
    EditText phone;
    Spinner  type;
    String id_user;
    String id_contact;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        name = findViewById(R.id.inputContactName);
        address = findViewById(R.id.inputContactAddress);
        phone = findViewById(R.id.inputContactPhone);
        type = findViewById(R.id.SpinnerContactType);
        title = findViewById(R.id.titleContact);
        id_user = getIntent().getStringExtra("ID_USER");
        id_contact = getIntent().getStringExtra("ID_CONTACT");
        loadContact();
    }

    public void SaveContact(View view){

        String nameValue = name.getText().toString();
        String addressValue = address.getText().toString();
        String phoneValue = phone.getText().toString();
        String typeValue = type.getSelectedItem().toString();
        Contact contact = new Contact(nameValue,addressValue,phoneValue,typeValue);
        ContactServices contactServices = new ContactServices();
        boolean status;
        if(id_contact !=null){
            contact.setUuid(id_contact);
            status = contactServices.Update(id_user,contact);
        }else{
            status = contactServices.Create(id_user,contact);
        }

        String messageStatus = id_contact== null?"Contato salvo com sucesso!":"Contato atualizado com sucesso!";
        AlertDialog.Builder builder = new AlertDialog.Builder(Contato.this);
        builder.setTitle( status? messageStatus : "Erro, tente novamente!" );
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.Route_Start(Contato.this,ListContact.class,id_user);
            }
        });

        builder.show();

    }

    void loadContact(){
        if(id_contact != null){
         title.setText("Atualizar contato");
        ContactServices contactServices = new ContactServices();
        Contact contact = contactServices.FindById(id_user,id_contact);
        name.setText(contact.getName());
        address.setText(contact.getAddress());
        phone.setText(contact.getPhone());
        }

    }
}