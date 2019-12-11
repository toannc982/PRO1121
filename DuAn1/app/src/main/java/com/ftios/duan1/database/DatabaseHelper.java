package com.ftios.duan1.database;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftios.duan1.R;

import java.io.IOException;
import java.io.InputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 14;

    public static final String DATABASE_NAME = "tracnghiem";

    final AssetFileDescriptor is;


    public DatabaseHelper(Context context) throws IOException {
            super(context, DATABASE_NAME, null, VERSION);

          is = context.getAssets().openFd("tracnghiem1.db");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.valueOf(is));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "tracnghiem");


        onCreate(db);
    }

}
