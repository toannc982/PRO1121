package com.ftios.duan1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ftios.duan1.database.DBHelper;
import com.ftios.duan1.database.DatabaseHelper;
import com.ftios.duan1.model.Cauhoi;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by T-420 on 28/05/2017.
 */

public class cauhoi_controller {
    private DBHelper dbHelper;

    public cauhoi_controller(Context context){

            dbHelper = new DBHelper(context);

    }

    //lấy danh sách câu hỏi
    public ArrayList<Cauhoi> getCauhoi(int sode, String monhoc){
        ArrayList<Cauhoi> lsCauhoi = new ArrayList<Cauhoi>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem WHERE num_exam = '"+sode+"' AND subject = '"+monhoc+"' ORDER BY random()",null);

        cursor.moveToFirst();   // duyệt từ dòng đầu tiên
        do{
            Cauhoi item;
            item = new Cauhoi(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getString(8), cursor.getString(9), "");
            lsCauhoi.add(item);
        }while (cursor.moveToNext());
        return lsCauhoi;
    }

    //lấy danh sách câu hỏi theo câu hỏi
    public Cursor getTKCauhoi(String monhoc, String tukhoa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem WHERE question LIKE '%"+tukhoa+"%' AND subject LIKE '%"+monhoc+"%'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }

    //Lấy danh sách câu hỏi theo môn học
    public Cursor getTKMonHOc(String tukhoa) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tracnghiem WHERE subject LIKE '%"+tukhoa+"%'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return  cursor;
    }
}
