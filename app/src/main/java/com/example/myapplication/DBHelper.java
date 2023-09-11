package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Vector;

public class DBHelper {

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        this.context = context;
    }

    public DBHelper OpenDB() {
        DBConnector dbCon = new DBConnector(context);
        sqLiteDatabase = dbCon.getWritableDatabase();
        sqLiteDatabase = dbCon.getReadableDatabase();
        return this;
    }

    public boolean InsertUsers(UserInfo userInfo) {
        try {
            sqLiteDatabase.execSQL("insert into UserInfoTable values('" + userInfo.getFirstName() + "','" + userInfo.getLastName() + "','" + userInfo.getAddress() + "','" + userInfo.getEmail() + "','" + userInfo.getPhone() + "','" + userInfo.getUsername() + "','" + userInfo.getPassword() + "','" + userInfo.getUserType() + "')");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<UserInfo> ValidLogin(String Username, String Password) {
        ArrayList<UserInfo> userList = new ArrayList<UserInfo>();

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from UserInfoTable where   Username ='" + Username + "' and Password='" + Password + "'", null);
            if (cursor.moveToFirst()) {
                do {
                    UserInfo user = new UserInfo();
                    user.setUsername(cursor.getString(5));
                    user.setPassword(cursor.getString(6));
                    user.setUserType(cursor.getString(7));
                    userList.add(user);
                } while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public Cursor SearchAllProperty() {
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT  Status FROM CustomerInfo", null);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return cursor;
    }
    public ArrayList<CleaningPro> SearchProperty(String CustomerId) {
        ArrayList<CleaningPro> productList = new ArrayList<CleaningPro>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from CustomerInfo where CustomerId='" + CustomerId + "'", null);
            if (cursor.moveToFirst()) {
                do {


                    CleaningPro product = new CleaningPro();
                    product.setHoId(cursor.getInt(0));
                    product.setCustomerName(cursor.getString(1));
                    product.setCustomerAddress(cursor.getString(2));
                    product.setCustomerPhone(cursor.getString(3));
                    product.setRooms(cursor.getInt(4));
                    product.setLiving(cursor.getInt(5));
                    product.setBath(cursor.getInt(6));
                    product.setKitchan(cursor.getInt(7));
                    product.setFloor(cursor.getInt(8));
                    product.sethOImg(cursor.getBlob(9));
                    product.setStatus(cursor.getString(10));
                    product.setTotalPrice(cursor.getInt(11));

                    productList.add(product);


                } while ((cursor.moveToNext()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return productList;
    }

    public boolean save(String Name,String Address, String Phone, int Rooms, int Living,int Kitchen,int Floor,int Bath,byte[] pp, int totalPrice){
        try{
            ContentValues cv = new ContentValues();
            cv.put("CustomerName",Name );
            cv.put("CustomerAddress", Address);
            cv.put("CustomerPhone", Phone);
            cv.put("Rooms",Rooms );
            cv.put("Living ",Living );
            cv.put("Bath",Bath );
            cv.put("Kitchan",Kitchen);
            cv.put("Floor",Floor );
            cv.put("PP",pp);
            cv.put("TotalPrice", totalPrice);
            sqLiteDatabase.insert("CustomerInfo", null, cv);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertOrderDetails(String Username) {
        try {
            sqLiteDatabase.execSQL("INSERT INTO Orders (CustomerUsername) VALUES ('" + Username + "')");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderTable(String Username, String postId) {
        try {
            sqLiteDatabase.execSQL("UPDATE Orders SET CleanerUsername = '" + Username + "' WHERE OrderId = '"+ postId +"'");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateHouseTable(String postId) {
        try {
            sqLiteDatabase.execSQL("UPDATE CustomerInfo SET Status = 'Booked' WHERE CustomerId = '"+ postId +"' ");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vector<String> getCleanerName(String Username) {
        Vector<String> veCleaner = new Vector<>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select CleanerUsername from Orders WHERE CustomerUsername = '" + Username +"' GROUP BY CleanerUsername" , null);
            if (cursor.moveToFirst()) {
                do {
                    veCleaner.add(cursor.getString(0));
                }
                while (cursor.moveToNext());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return veCleaner;
    }


    public boolean insertReview( FeedBackClass feedBackClass) {
        try {
            sqLiteDatabase.execSQL("INSERT INTO Review(CustomerUsername,Description,CleanerUsername) VALUES ('" + feedBackClass.getCustomerUsername() + "','"+ feedBackClass.getDescrip() +"', '" + feedBackClass.getCleanerUsername() + "')");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor selectAllReviews() {
        Cursor cursor = null;
        try {
            cursor = sqLiteDatabase.rawQuery("SELECT CustomerUsername FROM Review", null);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return cursor;
    }

    //search product
    public ArrayList<FeedBackClass> selectReview(String reviewId) {
        ArrayList<FeedBackClass> houseList = new ArrayList<FeedBackClass>();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("Select * from Review where ReviewId='" + reviewId + "'", null);
            if (cursor.moveToFirst()) {
                do {
                    FeedBackClass feedBackClass = new FeedBackClass();

                    feedBackClass.setReviewId(cursor.getInt(0));
                    feedBackClass.setCustomerUsername(cursor.getString(1));
                    feedBackClass.setDescrip(cursor.getString(2));
                    feedBackClass.setCleanerUsername(cursor.getString(3));


                    houseList.add(feedBackClass);
                } while ((cursor.moveToNext()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return houseList;
    }


}
