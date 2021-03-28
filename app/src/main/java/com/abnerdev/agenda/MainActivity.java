package com.abnerdev.agenda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abnerdev.agenda.Services.UserServices;

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
        UserServices userServices = new UserServices();
        String auth = userServices.autentication(emailValue, passwordValue);
        if (auth == null) {
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
            title.setText(auth);
        }


    }

    public void ButtonSignIn(View view) {
        Intent intent = new Intent(MainActivity.this,
                SignIn.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}