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

public class FeedBackAll extends AppCompatActivity {
    ListView ListViewAllPost;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_all);

        dbHelper=new DBHelper(this);
        dbHelper.OpenDB();
        ListViewAllPost = (ListView)findViewById(R.id.lvViewAllPostList);

        ArrayList<String> theList = new ArrayList<>();
        Cursor cursor = dbHelper.selectAllReviews();
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Data found",Toast.LENGTH_LONG).show();

        }
        else{
            while(cursor.moveToNext()){

                theList.add(cursor.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                ListViewAllPost.setAdapter(listAdapter);
            }
        }

        ListViewAllPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String productId = "" + (position + 1);
                ArrayList<FeedBackClass>postList = dbHelper.selectReview(productId);

                if(postList.size()!=0){
                    Intent intentViewList = new Intent(FeedBackAll.this, FeedBackDescription.class);

                    FeedBackClass post = postList.get(0);

                    intentViewList.putExtra("ReviewID", String.valueOf(post.getReviewId()));
                    intentViewList.putExtra("CustomerUsername", post.getCustomerUsername());
                    intentViewList.putExtra("Description", post.getDescrip());
                    intentViewList.putExtra("CleanerUsername", post.getCleanerUsername());

                    startActivity(intentViewList);

                }

            }
        });



    }
}