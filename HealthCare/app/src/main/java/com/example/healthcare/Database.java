package com.example.healthcare;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.SplittableRandom;


/** @noinspection ALL*/
public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase) {
        String qry1 = "create table users(username text, email text , password text)";
        sqliteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text, product text, price float, otype text)";
        sqliteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)";
        sqliteDatabase.execSQL(qry3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username =? and product =?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype= ?", str); //where condition
        db.close();
    }

    public ArrayList<String> getCartData(String username, String otype) {
        ArrayList<String> cartItems = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {username, otype};
        Cursor cursor = db.query("cart", null, "username = ? and otype = ?", selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String product = cursor.getString(cursor.getColumnIndex("product"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                cartItems.add(product + " $" + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cartItems;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();

    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("Select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7) + "$" + c.getString(8)+c.getString(9));
            } while (c.moveToNext());
        }
        db.close();
        return arr;

    }
//public ArrayList<String> getOrderData(String username) {
//    ArrayList<String> arr = new ArrayList<>();
//    SQLiteDatabase db = getReadableDatabase();
//    String[] selectionArgs = {username};
//    Cursor cursor = null;
//
//    try {
//        // Selecting all columns from the "orderplace" table where the username matches
//        cursor = db.rawQuery("SELECT * FROM orderplace WHERE username = ?", selectionArgs);
//
//        // Checking if the cursor contains any rows
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                // Retrieving data from each column and adding to the ArrayList
//                String rowData = cursor.getString(0) + "$" + // Assuming username is in the first column
//                        cursor.getString(1) + "$" +
//                        cursor.getString(2) + "$" +
//                        cursor.getString(3) + "$" +
//                        cursor.getString(4) + "$" +
//                        cursor.getString(5) + "$" +
//                        cursor.getString(6) + "$" +
//                        cursor.getString(7) + "$" +
//                        cursor.getString(8);
//
//                arr.add(rowData);
//            } while (cursor.moveToNext());
//        }
//    } catch (SQLException e) {
//
//        Log.e("getOrderData", "Error retrieving order data: " + e.getMessage());
//    } finally {
//        // Closing the cursor and database connection
//        if (cursor != null) {
//            cursor.close();
//        }
//        db.close();
//    }
//
//    return arr;
//}
    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
int result=0;
String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?", str);
        if (c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

}

