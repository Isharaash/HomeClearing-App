package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer extends AppCompatActivity {
    Button ButtonProperty, ButtonAccepted,ButtonFeedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        ButtonProperty=(Button) findViewById(R.id.btnCleaningProperty);

        ButtonFeedBack=(Button) findViewById(R.id.btnFeedBack);

        ButtonProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(Customer.this,CleaningProperty.class);
                startActivity(intentRegister);
            }
        });


        ButtonFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(Customer.this,FeedBack.class);
                startActivity(intentRegister);
            }
        });


    }
}