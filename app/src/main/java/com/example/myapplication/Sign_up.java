package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {
    EditText EditTextFirstName,EditTextLastName,EditTextAddress,EditTextEmail,EditTextPhone,EditTextUsername,EditTextPassword,EditTextConfirmPassword;
    Spinner SpinnerUserType;
    Button ButtonRegister;

    private DBHelper dbHelper;
    String UserType[]={"Customer","Cleaner"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditTextFirstName=( EditText)findViewById(R.id.txtFName);
        EditTextLastName=( EditText)findViewById(R.id.txtLName);
        EditTextAddress=( EditText)findViewById(R.id.txtAddress);
        EditTextEmail=( EditText)findViewById(R.id.txtEmail);
        EditTextPhone=( EditText)findViewById(R.id.txtPhone);
        EditTextUsername=( EditText)findViewById(R.id.txtusername);
        EditTextPassword=( EditText)findViewById(R.id.txtPassword);
        EditTextConfirmPassword=( EditText)findViewById(R.id.txtConPassword);
        SpinnerUserType=(Spinner)findViewById(R.id.spnUserType);
        ButtonRegister=(Button)findViewById(R.id.btnRegister2);

        dbHelper =new DBHelper(this);
        dbHelper.OpenDB();



        ArrayAdapter ad= new ArrayAdapter(this, android.R.layout.simple_spinner_item,UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_item);
        SpinnerUserType.setAdapter(ad);
        ButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(  EditTextFirstName.getText().toString().isEmpty() || EditTextLastName.getText().toString().isEmpty() || EditTextPassword.getText().toString().isEmpty() || EditTextConfirmPassword.getText().toString().isEmpty() ){
                    Toast.makeText(getApplicationContext(),"Fields can't be blank",Toast.LENGTH_LONG).show();
                }
                else if(EditTextPassword.getText().toString().length()<3){
                    Toast.makeText(getApplicationContext(),"Passowrd Must have more than 3 Character",Toast.LENGTH_LONG).show();
                }
                else if(!EditTextPassword.getText().toString().equals(EditTextConfirmPassword.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Passowrd and confirm password should match",Toast.LENGTH_LONG).show();
                }
                else if(EditTextPhone.getText().toString().length()<10){
                    Toast.makeText(getApplicationContext(),"Phone Number must have 10 numbers only",Toast.LENGTH_LONG).show();
                }
                else if(EditTextPhone.getText().toString().length()>10){
                    Toast.makeText(getApplicationContext(),"Phone Number must have 10 numbers only",Toast.LENGTH_LONG).show();
                }
                else{
                    UserInfo userclass=new UserInfo(EditTextFirstName.getText().toString(), EditTextLastName.getText().toString(),EditTextAddress.getText().toString(),  EditTextEmail.getText().toString(), EditTextPhone.getText().toString(),EditTextUsername.getText().toString(),   EditTextPassword.getText().toString(),
                            SpinnerUserType.getSelectedItem().toString());

                    if(dbHelper.InsertUsers(userclass)){
                        Toast.makeText(getApplicationContext(),"your account is created",Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), EditTextFirstName.getText().toString()+EditTextLastName.getText().toString()+"has a"+SpinnerUserType.getSelectedItem().toString() ,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"your account is Creation Failed",Toast.LENGTH_LONG).show();

                    }


                }


            }
        });




    }
}