package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewListProperty extends AppCompatActivity {
 EditText EditTextcustomerName,EditTextcustomerAddrees,EditTextcustomerPhone,EditTextRoom,EditTextLiving,EditTextBath,EditTextKitchan, EditTextFloor, TotalPrice;
    ImageView ImageViewShow;
    Button ButtonAccept;
    DBHelper dbHelper;
    Login.Just just = new Login.Just();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_list_property);
        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        EditTextcustomerName=(EditText)findViewById(R.id.txtPrName);
        EditTextcustomerAddrees=(EditText)findViewById(R.id.txtPrAddress);
        EditTextcustomerPhone=(EditText)findViewById(R.id.txtPrPhone);
        EditTextRoom=(EditText)findViewById(R.id.txtPrRooms);
        EditTextLiving=(EditText)findViewById(R.id.txtPrLiving);
        EditTextBath=(EditText)findViewById(R.id.txtPrBath);
        EditTextKitchan=(EditText)findViewById(R.id.txtPrKitchan);
        EditTextFloor=(EditText)findViewById(R.id.txtPrFloor);
        ImageViewShow = (ImageView) findViewById(R.id.iv_MainActivityPPShow);
        ButtonAccept = (Button) findViewById(R.id.btnAccept);
        TotalPrice = (EditText) findViewById(R.id.txtTotalPrice);

        Intent intent=this.getIntent();

        String postId =intent.getStringExtra("CustomerId");
        EditTextcustomerName.setText((intent.getStringExtra("CustomerName")));
        EditTextcustomerAddrees.setText((intent.getStringExtra("CustomerAddress")));
        EditTextcustomerPhone.setText((intent.getStringExtra("CustomerPhone")));
        EditTextRoom.setText((intent.getStringExtra("Rooms")));
        EditTextLiving.setText((intent.getStringExtra("Living")));
        EditTextBath.setText((intent.getStringExtra("Bath")));
        EditTextKitchan.setText((intent.getStringExtra("Kitchan")));
        EditTextFloor.setText((intent.getStringExtra("Floor")));
        ImageViewShow.setImageBitmap(covertByteArrayToBitmap(intent.getByteArrayExtra("PP")));
        TotalPrice.setText((intent.getStringExtra("TotalPrice")));

        String status=intent.getStringExtra("Status") ;

        String userName = just.CleanerUsername;

        ButtonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(status.equals("Available")) {

                    if (dbHelper.updateHouseTable(postId) && dbHelper.updateOrderTable(userName, postId )) {
                        Toast.makeText(getApplicationContext(),userName+"Accepted the job"+postId,Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Didn't accepted the job", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Already Booked", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private Bitmap covertByteArrayToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);

    }
}