package com.ftios.duan1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ftios.duan1.database.DBHelper;
import com.ftios.duan1.model.Account;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {

    public static final String TABLE_NAME = "nguoidung";

    public static final String TAG = "NguoiDungDAO";

    private SQLiteDatabase db;
    private DBHelper dbHepler;


    public NguoiDungDAO(Context context) {
        dbHepler = new DBHelper(context);
        db = dbHepler.getWritableDatabase();
    }


    public boolean checkNguoiDung(String user) {

        String query = "select * from " + TABLE_NAME + " where email = '" + user + "'";
        Cursor cursor = db.rawQuery(query, null);
        Log.e("query", cursor.getCount() + " " + query);

        if (cursor.getCount() > 0) {
            return false;
        } else {
            return true;
        }
    }


    public boolean insertNguoiDung(Account nd) {
        ContentValues values = new ContentValues();
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("email", nd.getEmail());
        values.put("name", nd.getName());
        values.put("birthday", nd.getBirthday());
        long result = db.insert(TABLE_NAME, null, values);

        try {
            if (result == -1) {
                return false;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
            return false;
        }

        return true;
    }

//check

    public boolean checkLogin(String username, String password) {

        String query = "select * from " + TABLE_NAME + " where email = '" + username + "' AND  password = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        Log.e("query", cursor.getCount() + " " + query);

        if (cursor.getCount() > 0) return true;
        else return false;
    }


    public boolean updateInfoNguoiDung(String email, String name, String birthday, String phone) {
        ContentValues values = new ContentValues();
        values.put("birthday", birthday);
        values.put("name", name);
        values.put("phone", phone);
        int result = db.update(TABLE_NAME, values, "email=?", new
                String[]{email});
        if (result == 0) {
            return false;
        }
        return true;
    }

    public Account searchUser(String name) {
        Account u = new Account();

        String query = "select * from " + TABLE_NAME + " where email = '" + name + "' ";
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            u.setName(c.getString(c.getColumnIndex("name")));
            u.setEmail(c.getString(c.getColumnIndex("email")));
            u.setBirthday(c.getString(c.getColumnIndex("birthday")));
            u.setPhone(c.getString(c.getColumnIndex("phone")));
            u.setPassword(c.getString(c.getColumnIndex("password")));

            return u;

        }else {
            Log.e("error not found", "user can't be found or database empty");
            return u;
        }

    }


}
