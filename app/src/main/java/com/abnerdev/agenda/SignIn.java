package com.abnerdev.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.abnerdev.agenda.Model.User;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

public class SignIn extends AppCompatActivity {

    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        email = findViewById(R.id.idEmailLogin);
        password = findViewById(R.id.idEmailLogin);
    }

    public void SignIn(View view){
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        User user = new User(email,password);
        UserServices userServices = new UserServices();
        userServices.create(user);
        Toast.makeText(SignIn.this,"Cadastrado com sucesso!",Toast.LENGTH_LONG);
        Utils.Route_Start(SignIn.this,MainActivity.class,null);
        //Intent intent = new Intent(SignIn.this,MainActivity.class);
        //startActivity(intent);
    }




}