package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;

public class FeedBack extends AppCompatActivity {
    EditText editText, textReview;
    Spinner SpinnerCleanersName;
    Button ButtonAddReview;
    private DBHelper dbHelper;

    Login.Just just = new Login.Just();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        String Username = just.Username;
        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();

        editText = (EditText) findViewById(R.id.txtNewUsername);
        textReview = (EditText) findViewById(R.id.txtReview);
        SpinnerCleanersName = (Spinner) findViewById(R.id.spnCleanerUsername) ;
        editText.setText(Username);
        ButtonAddReview = (Button) findViewById(R.id.btnAddReview);
        Vector<String> veCategory = dbHelper.getCleanerName(Username);

        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_spinner_item,veCategory);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SpinnerCleanersName.setAdapter(ad);


        ButtonAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SpinnerCleanersName.getSelectedItem().toString().equals("Pending...")) {
                    Toast.makeText(FeedBack.this, "Select Cleaner", Toast.LENGTH_SHORT).show();
                }
                else {
                    FeedBackClass feedBackClass = new FeedBackClass(editText.getText().toString(),textReview.getText().toString(),SpinnerCleanersName.getSelectedItem().toString());

                    if (dbHelper.insertReview(feedBackClass)) {
                        Toast.makeText(FeedBack.this, "Feedback was submitted", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(FeedBack.this, "Feedback wasn't submitted", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });




    }
}