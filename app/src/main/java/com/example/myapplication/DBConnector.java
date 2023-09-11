package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBConnector extends SQLiteOpenHelper {
    public DBConnector(Context context)
    {
        super(context,"Cleaning10",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table UserInfoTable (FName VARCHAR,LName VARCHAR,Address VARCHAR,Email VARCHAR, Phone VARCHAR,Username PRIMARY KEY  NOT NULL,Password VARCHAR,UserType VARCHAR)");
        sqLiteDatabase.execSQL("create table CustomerInfo (CustomerId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,CustomerName VARCHAR,CustomerAddress VARCHAR,CustomerPhone VARCHAR,Rooms INTEGER,Living INTEGER, Bath INTEGER,Kitchan INTEGER,Floor INTEGER, PP BLOB, Status VARCHAR DEFAULT 'Available', TotalPrice INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE Orders(OrderId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CustomerUsername VARCHAR, CleanerUsername VARCHAR DEFAULT 'Pending...',FOREIGN KEY (CustomerUsername) REFERENCES UserInfoTable (Username))");
        sqLiteDatabase.execSQL("CREATE TABLE Review(ReviewId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CustomerUsername VARCHAR, Description VARCHAR, CleanerUsername VARCHAR, FOREIGN KEY (CustomerUsername) REFERENCES UserInfoTable (Username))");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
