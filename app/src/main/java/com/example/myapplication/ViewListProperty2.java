package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewListProperty2 extends AppCompatActivity {
    ListView ListViewPropertyList;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_property2);


        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();
        ListViewPropertyList=(ListView)findViewById(R.id.lvProperty);

        ArrayList<String>theList=new ArrayList<>();
        Cursor cursor=dbHelper. SearchAllProperty();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_LONG).show();

        }
        else{
            while(cursor.moveToNext()){
                theList.add(cursor.getString(0));
                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                ListViewPropertyList.setAdapter(listAdapter);
            }
        }
        ListViewPropertyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String productId = "" + (position + 1);
                ArrayList<CleaningPro> ProductList = dbHelper.SearchProperty(productId);

                if (ProductList.size() != 0) {
                    Intent intentViewList = new Intent(ViewListProperty2.this, ViewListProperty.class);

                    CleaningPro cleaningPro = ProductList.get(0);
                    intentViewList.putExtra("CustomerId",String.valueOf (cleaningPro.getHoId()));
                    intentViewList.putExtra("CustomerName", cleaningPro.getCustomerName());
                    intentViewList.putExtra("CustomerAddress", cleaningPro.getCustomerAddress());
                    intentViewList.putExtra("CustomerPhone", cleaningPro.getCustomerPhone());
                    intentViewList.putExtra("Rooms", String.valueOf(cleaningPro.getRooms()));
                    intentViewList.putExtra("Living", String.valueOf(cleaningPro.getLiving()));
                    intentViewList.putExtra("Bath", String.valueOf(cleaningPro.getBath()));
                    intentViewList.putExtra("Kitchan", String.valueOf(cleaningPro.getKitchan()));
                    intentViewList.putExtra("Floor", String.valueOf(cleaningPro.getFloor()));
                    intentViewList.putExtra("PP", cleaningPro.gethOImg());
                    intentViewList.putExtra("Status",cleaningPro.getStatus());
                    intentViewList.putExtra("TotalPrice", String.valueOf(cleaningPro.getTotalPrice()));

                    startActivity(intentViewList);
                }

            }
        });




    }
}