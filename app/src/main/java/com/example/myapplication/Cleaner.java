package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cleaner extends AppCompatActivity {
   Button ButttonViewProperty, ButttonFeedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner);

        ButttonViewProperty=(Button) findViewById(R.id.btnClener);
        ButttonFeedBack=(Button) findViewById(R.id.btnfeedback);
     ButttonViewProperty.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
              Intent intentRegister=new Intent(Cleaner.this,ViewListProperty2.class);
               startActivity(intentRegister);
            }      });

        ButttonFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRegister=new Intent(Cleaner.this,FeedBackAll.class);
                startActivity(intentRegister);
            }      });



    }
}