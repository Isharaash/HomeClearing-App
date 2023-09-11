package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText EditTextUsername, EditTextPassword;
    Button ButtonLogin;

    public static class Just{
        public static String Username;
        public static String pass;
        public static String CleanerUsername;
    }

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditTextUsername=(EditText)findViewById(R.id.txtUserId2);
        EditTextPassword=(EditText)findViewById(R.id.txtPassword2);
        ButtonLogin=(Button) findViewById(R.id.btnLogin2);

        dbHelper =new DBHelper(this);
        dbHelper.OpenDB();


        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Just just = new Just();
                just.Username = EditTextUsername.getText().toString();
                just.pass = EditTextPassword.getText().toString();

                if ( EditTextUsername.getText().toString().isEmpty() || EditTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields Can't be Blank",
                            Toast.LENGTH_SHORT).show();
                }


                ArrayList<UserInfo> userDetails = dbHelper.ValidLogin(just.Username,just.pass);

                if (userDetails.size()!=0)
                {
                    UserInfo user = userDetails.get(0);
                    String UserType = user.getUserType();
                    Toast.makeText(getApplicationContext(), "User found "+UserType, Toast.LENGTH_LONG).show();

                    if (UserType.equals("Customer")) {

                        Intent intentLogin= new Intent(Login.this, Customer.class);
                        startActivity(intentLogin);
                    }

                    else {
                        just.CleanerUsername=user.getUsername();

//                        Toast.makeText(getApplicationContext(), "User found "+UserType, Toast.LENGTH_LONG).show();
                        Intent intentLogin = new Intent(Login.this,Cleaner .class);
                        startActivity(intentLogin);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();

                }


            }
        });



    }
}