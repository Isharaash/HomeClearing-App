package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CleaningProperty extends AppCompatActivity {
    EditText EditTextCustomerName,EditTextAddress,EditTextPhone,EditTextRooms,EditTextBathroom,EditTextLivingrooms,EditTextFloors,EditTextKitchan;
    ImageView ImageViewAddImages, ImageViewCamera,ImageViewShow,ImageViewGallery;
    Button ButtonSubmit;

    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning_property);

        dbHelper = new DBHelper(this);
        dbHelper.OpenDB();

        Login.Just just = new Login.Just();

        EditTextCustomerName = (EditText) findViewById(R.id.txtProName);
        EditTextAddress = (EditText) findViewById(R.id.txtProAddress);
        EditTextPhone = (EditText) findViewById(R.id.txtProNumber);
        EditTextRooms = (EditText) findViewById(R.id.txtRooms);
        EditTextLivingrooms = (EditText) findViewById(R.id.txtLiving);
        EditTextBathroom = (EditText) findViewById(R.id.txtBath);
        EditTextKitchan = (EditText) findViewById(R.id.txtKitchan);
        EditTextFloors = (EditText) findViewById(R.id.txtFloor);
        ButtonSubmit = (Button) findViewById(R.id.btnSubmit);
        ImageViewAddImages= (ImageView) findViewById(R.id.imageViewActivityProfilePicture);
        ImageViewShow = (ImageView) findViewById(R.id.iv_MainActivityPPShow);

        String Username = just.Username;

        ImageViewAddImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseProfilePicture();
            }
        });

        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int totalPrice = 0;

                if (EditTextCustomerName.getText().toString().isEmpty() || EditTextAddress.getText().toString().isEmpty() || EditTextPhone.getText().toString().isEmpty() || EditTextRooms.getText().toString().isEmpty() || EditTextLivingrooms.getText().toString().isEmpty() || EditTextBathroom.getText().toString().isEmpty() || EditTextKitchan.getText().toString().isEmpty() ||
                        EditTextFloors.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields can't be blank", Toast.LENGTH_LONG).show();
                }
                else if(EditTextPhone.getText().toString().length()<10){
                    Toast.makeText(getApplicationContext(),"Phone Number must have 10 numbers only",Toast.LENGTH_LONG).show();
                }
                else if(EditTextPhone.getText().toString().length()>10){
                    Toast.makeText(getApplicationContext(),"Phone Number must have 10 numbers only",Toast.LENGTH_LONG).show();
                }
                else {

                    totalPrice =(Integer.parseInt(  EditTextRooms.getText().toString())*500)+
                            (Integer.parseInt( EditTextLivingrooms .getText().toString())*450)+
                            (Integer.parseInt(EditTextBathroom.getText().toString())*550)+
                           (Integer.parseInt( EditTextKitchan.getText().toString())*600)+
                            (Integer.parseInt(EditTextFloors.getText().toString())*500);

                    byte[] byteSSP = covertImageToByteArray(ImageViewAddImages);

                    if(dbHelper.save(EditTextCustomerName.getText().toString(), EditTextAddress.getText().toString(),  EditTextPhone.getText().toString(),
                            Integer.parseInt(EditTextRooms.getText().toString()), Integer.parseInt(EditTextLivingrooms.getText().toString()),
                            Integer.parseInt(EditTextBathroom.getText().toString()),Integer.parseInt(EditTextKitchan.getText().toString()),Integer.parseInt(EditTextFloors.getText().toString()),byteSSP, totalPrice) && dbHelper.insertOrderDetails(Username)) {
                        Toast.makeText(getApplicationContext(),"All details were submitted  + " + totalPrice,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"All details weren't submitted",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }


    private byte[]covertImageToByteArray(ImageView imageView){
        Bitmap bitmap=((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80,byteArrayOutputStream);
        return  byteArrayOutputStream.toByteArray();
    }

    private void ChooseProfilePicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CleaningProperty.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_dialog_picture, null);
        builder.setCancelable(false);
        builder.setView(dialogView);

        ImageViewCamera = dialogView.findViewById(R.id.imageViewDPPCamera);
        ImageViewGallery = dialogView.findViewById(R.id.imageViewDPPGallery);
        final AlertDialog alertDialogProfilePicture = builder.create();
        alertDialogProfilePicture.show();


        ImageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAndRequestPermission()) {
                    takePictureFromCamera();
                    alertDialogProfilePicture.cancel();
                }
            }
        });

        ImageViewGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
                alertDialogProfilePicture.cancel();
            }
        });
    }
    private void takePictureFromGallery() {
        Intent pickPhoto=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto,1);

    }
    private void takePictureFromCamera() {

        Intent takePicture=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePicture.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(takePicture,2);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if(resultCode==RESULT_OK) {
                    Uri selectImageUri=data.getData();
                    ImageViewAddImages.setImageURI(selectImageUri);
                }
                break;
            case 2:
                if(resultCode==RESULT_OK) {
                    Bundle bundle=data.getExtras();
                    Bitmap bitmapImage=(Bitmap) bundle.get("data");
                    ImageViewAddImages.setImageBitmap(bitmapImage);
                }
                break;
        }
    }

    private boolean checkAndRequestPermission() {
        if(Build.VERSION.SDK_INT>23) {
            int  cameraPermission= ActivityCompat.checkSelfPermission(CleaningProperty.this, Manifest.permission.CAMERA);
            if(cameraPermission== PackageManager.PERMISSION_DENIED)
            {
                ActivityCompat.requestPermissions(CleaningProperty.this,new String[]{Manifest.permission.CAMERA},20);
                return false;
            }
        }
        return true;
    }


}