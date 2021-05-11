package com.abnerdev.agenda;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.abnerdev.agenda.Model.Contact;
import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.ContactServices;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contato extends AppCompatActivity {

    EditText name;
    EditText address;
    EditText phone;
    Spinner  type;
    String id_user;
    String id_contact;
    TextView title;

    static final int CAMERA_PERMISSION_CODE = 2001;
    static final int CAMERA_INTENT_CODE = 3001;
    String picturePath;
    ImageView imageViewCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        name = findViewById(R.id.inputContactName);
        address = findViewById(R.id.inputContactAddress);
        phone = findViewById(R.id.inputContactPhone);
        type = findViewById(R.id.SpinnerContactType);
        title = findViewById(R.id.titleContact);
        id_user = UserRepositories.getInstance().getID_USER();
        id_contact = getIntent().getStringExtra("ID_CONTACT");
        imageViewCamera = findViewById(R.id.imageContact);
        loadContact();
    }

    public void SaveContact(View view){

        String nameValue = name.getText().toString();
        String addressValue = address.getText().toString();
        String phoneValue = phone.getText().toString();
        String typeValue = type.getSelectedItem().toString();
        Contact contact = new Contact(nameValue,addressValue,phoneValue,typeValue);
        ContactServices contactServices = ContactServices.getInstance(this.getApplicationContext());
        boolean status;
        if(id_contact !=null){
            contact.setUuid(id_contact);
            status = contactServices.Update(contact);
        }else{
            status = contactServices.Create(contact);
        }

        String messageStatus = id_contact== null?"Contato salvo com sucesso!":"Contato atualizado com sucesso!";
        AlertDialog.Builder builder = new AlertDialog.Builder(Contato.this);
        builder.setTitle( status? messageStatus : "Erro, tente novamente!" );
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.Route_Start(Contato.this,ListContact.class);
            }
        });

        builder.show();

    }

    void loadContact(){
        if(id_contact != null){
         title.setText("Atualizar contato");
        ContactServices contactServices = ContactServices.getInstance(this.getApplicationContext());
        Contact contact = contactServices.FindById(id_contact);
        name.setText(contact.getName());
        address.setText(contact.getAddress());
        phone.setText(contact.getPhone());

        }

    }

    public void buttonCameraClicked(View view){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestCameraPermission();
        }else{
            sendCameraIntent();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    void requestCameraPermission(){
        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(checkSelfPermission(Manifest.permission.CAMERA) !=
                    PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{
                        Manifest.permission.CAMERA
                },CAMERA_PERMISSION_CODE);
            }else{
                sendCameraIntent();
            }
        }else{
            Toast.makeText(Contato.this,
                    "No camera available",Toast.LENGTH_LONG).show();
        }
    }


    void sendCameraIntent(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION,true);
        if(intent.resolveActivity(getPackageManager()) != null){
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(new Date());
            String picName = "pic_"+timeStamp;
            File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File pictureFile = null;
            try {
                pictureFile = File.createTempFile(picName,".jpg",dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(pictureFile != null){
                picturePath = pictureFile.getAbsolutePath();
                Uri photoUri = FileProvider.getUriForFile(
                        Contato.this,
                        "com.abnerdev.agenda.fileprovider",
                        pictureFile
                );
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(intent,CAMERA_INTENT_CODE);

            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendCameraIntent();
            } else {
                Toast.makeText(Contato.this,
                        "Camera Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_INTENT_CODE){
            if(resultCode == RESULT_OK){
                File file = new File(picturePath);
                if(file.exists()){

                    imageViewCamera.setImageURI(Uri.fromFile(file));
                }
            }else{
                Toast.makeText(Contato.this,
                        "Problem getting the image from the camera app",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}