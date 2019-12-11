package com.ftios.duan1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.ftios.duan1.database.DBHelper;
import com.ftios.duan1.database.DatabaseHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class WelcomeActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private DBHelper dbHepler;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    001);

        } else {

            try {
                _copydatabase();

            } catch (IOException e) {
                e.printStackTrace();
            }

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();

                }
            }, 1000);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 001: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        _copydatabase();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                            finish();

                        }
                    }, 1000);
                } else {
                    finish();
                }
                return;
            }

        }
    }

    public void _copydatabase() throws IOException {

        final String path = "/data/data/com.ftios.duan1/databases/";
        final String Name = "dbtracnghiem.sqlite";


        final String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases";

        File dir = new File(dirpath);

        dir.delete();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        OutputStream myOutput = new FileOutputStream(path + Name);
        byte[] buffer = new byte[1024];
        int length;
        InputStream myInput = this.getAssets().open(Name);
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();

    }

//    public boolean checkNguoiDung(String user) {
//
//        dbHepler = new DBHelper(this);
//        db = dbHepler.getWritableDatabase();
//
//        String query = "select * from nguoidung where username = '" + user + "'";
//        Cursor cursor = db.rawQuery(query, null);
//        Log.e("query", cursor.getCount() + " " + query);
//
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
