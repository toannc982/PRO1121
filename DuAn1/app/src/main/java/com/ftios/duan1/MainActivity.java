package com.ftios.duan1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ftios.duan1.database.DBHelper;
import com.ftios.duan1.model.Account;
import com.ftios.duan1.slide.ScreenSlideActivity;
import com.ftios.duan1.ui.dashboard.DashboardFragment;
import com.ftios.duan1.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        try {



                _copydatabase();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void btnDangKi(View view) {
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }


    public void btnDangXuat(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        //xoa tinh trang luu tru truoc do
        edit.clear();
        edit.commit();

        Intent i = new Intent(MainActivity.this, MainActivity.class);  //your class
        startActivity(i);
        finish();
    }

    public void btnThaydoiThongtin(View view) {
        startActivity(new Intent(getApplicationContext(), AccountDetailsActivity.class));
    }

    public void btnThiToan(View view) {
        SharedPreferences preferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

        final String strEmail = preferences.getString("email", "");
        final String strPassword = preferences.getString("password", "");

        if (!strEmail.isEmpty() && !strPassword.isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), ScreenSlideActivity.class);
            intent.putExtra("monhoc", "toan");
            intent.putExtra("test", "yes");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Yêu cầu bạn đăng nhập !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnThiTiengAnh(View view) {
        SharedPreferences preferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

        final String strEmail = preferences.getString("email", "");
        final String strPassword = preferences.getString("password", "");

        if (!strEmail.isEmpty() && !strPassword.isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), ScreenSlideActivity.class);
            intent.putExtra("monhoc", "tienganh");
            intent.putExtra("test", "yes");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Yêu cầu bạn đăng nhập !!", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnHuongDan(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Hướng dẫn");
        builder.setMessage("- Xin chào bạn đến với ứng dụng học tập");
        builder.setPositiveButton("Đã đọc", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.iTTTT :

                SharedPreferences preferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

                final String strEmail = preferences.getString("email", "");
                final String strPassword = preferences.getString("password", "");

                if (!strEmail.isEmpty() && !strPassword.isEmpty()) {
                    startActivity(new Intent(this, AccountActivity.class));
                } else {
                    Toast.makeText(this, "Yêu cầu bạn đăng nhập !!", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.iTTUD :
                startActivity(new Intent(this, AboutActivity.class));
                break;

            case R.id.iDX :

                SharedPreferences preferences1 = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

                final String strEmail1 = preferences1.getString("email", "");
                final String strPassword1 = preferences1.getString("password", "");

                if (!strEmail1.isEmpty() && !strPassword1.isEmpty()) {
                    SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    //xoa tinh trang luu tru truoc do
                    edit.clear();
                    edit.commit();

                    Intent i = new Intent(MainActivity.this, MainActivity.class);  //your class
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(this, "Bạn chưa đăng nhập !!", Toast.LENGTH_SHORT).show();
                }

                break;
        }

        //onBackPressed();
        return true;
    }

    public void _copydatabase() throws IOException {

        final String path = "/data/data/com.ftios.duan1/databases/";
        final String Name = "dbtracnghiem.sqlite";


        final String dirpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases";

        File dir = new File(dirpath);

        dir.delete();

        if (!dir.exists()){
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


//    @Override
//    public void login() {
//
//    }
//
//    @Override
//    public void setErrorUsername() {
////         ProgressDialog dialog = new ProgressDialog(MainActivity.this);
////        dialog.setMessage("please wait...");
////        dialog.show();
//
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.fragment_dashboard, null);
//
//
//        EditText editText = (EditText) dialogView.findViewById(R.id.edtEmail);
//
//        editText.setError("13");
//    }
//
//    @Override
//    public void setErrorPassword() {
//
//    }
//
//    @Override
//    public void navigate() {
//
//    }
//
//    @Override
//    public void rememberPassword() {
//
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
}
