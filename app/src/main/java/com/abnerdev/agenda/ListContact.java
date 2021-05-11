package com.abnerdev.agenda;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

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
    Context context;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);
        Id_user = UserRepositories.getInstance().getID_USER();
        listView = findViewById(R.id.recycleView);
        context = this.getApplicationContext();
        contactArrayAdapter = new ContactArrayAdapter(context);
        listView.setAdapter(contactArrayAdapter);
        listView.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ListContact.this,DividerItemDecoration.VERTICAL);
        listView.addItemDecoration(itemDecoration);
        contactArrayAdapter.notifyDataSetChanged();
        contactArrayAdapter.setClickListener(new ContactArrayAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                String Id_contact = UserServices.getInstance(context).FindById().getPhoneBook().getContato().get(position).getUuid();
                Intent intent = new Intent(ListContact.this, Contato.class);
                intent.putExtra("ID_CONTACT", Id_contact);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(int position, View view) {
                String Id_contact = UserServices.getInstance(context).FindById().getPhoneBook().getContato().get(position).getUuid();
                AlertDialog.Builder builder = new AlertDialog.Builder(ListContact.this);
                builder.setTitle("Tem certeza que deseja excluir ?");
                builder.setCancelable(false);
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ContactServices.getInstance(context).Delete(Id_contact);
                                contactArrayAdapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
                builder.show();

                return true;
            }

        });

        seekBar =  findViewById(R.id.seekBarGrid);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { //Clica em cima da bolinha - chama essa função
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBarStop) { //Solta a bolinha - chama essa função
                listView.setLayoutManager(new GridLayoutManager(ListContact.this,seekBarStop.getProgress()+1));
            }
        });

    }

    public void AddContact(View view){
        /*ContactServices contactServices = new ContactServices();
        contactServices.Create(UserRepositories.getInstance().getID_USER(),
                new Contact("aa","419999","4444444","Casa"));*/
        Utils.Route_Start(ListContact.this,Contato.class);
        // Notificar, para atualizar a lista.

    }

    @Override
    protected void onResume() {
        super.onResume();
        contactArrayAdapter.notifyDataSetChanged();
    }



}