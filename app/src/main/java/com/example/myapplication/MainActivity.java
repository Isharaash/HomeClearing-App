package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button ButtonRegister, ButtonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButtonLogin=(Button) findViewById(R.id.btnLogin);
        ButtonRegister=(Button) findViewById(R.id.btnRegister);


        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(MainActivity.this,Login.class);
                startActivity(intentRegister);
            }
        });

        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(MainActivity.this,Sign_up.class);
                startActivity(intentRegister);
            }
        });


    }
}