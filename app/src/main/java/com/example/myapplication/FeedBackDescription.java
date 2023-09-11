package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FeedBackDescription extends AppCompatActivity {
    TextView EditTextCustomerUsername, EditTextViewDescription;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_description);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextCustomerUsername = (TextView) findViewById(R.id.txtVCustomerName);
        EditTextViewDescription = (TextView) findViewById(R.id.txtVDescription);

        Intent intent = this.getIntent();

        EditTextCustomerUsername.setText((intent.getStringExtra("CustomerUsername")));
        EditTextViewDescription.setText((intent.getStringExtra("Description")));


    }
}