package com.abnerdev.agenda;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.abnerdev.agenda.Repositories.UserRepositories;
import com.abnerdev.agenda.Services.UserServices;
import com.abnerdev.agenda.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        email = findViewById(R.id.idEmailLogin);
        password = findViewById(R.id.idPasswordLogin);
        title = findViewById(R.id.titleLogin);

    }

    public void AuthenticationLogin(View view) {
        String emailValue = email.getText().toString();
        String passwordValue = password.getText().toString();
        UserServices userServices = UserServices.getInstance();
         boolean auth = userServices.autentication(emailValue, passwordValue);
        if (!auth) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("E-mail ou senha incorreta!");
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    email.setText("");
                    password.setText("");
                }
            });
            builder.create().show();
        } else {

            Utils.Route_Start(MainActivity.this,ListContact.class);
        }
    }

    public void ButtonSignIn(View view) {
        Utils.Route_Start(MainActivity.this,SignIn.class);
    }
}