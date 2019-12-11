package com.ftios.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ftios.duan1.dao.NguoiDungDAO;
import com.ftios.duan1.model.Account;

public class AccountActivity extends AppCompatActivity {

    private TextView tvName;
    private EditText edtEmail, edtPassword, edtBirthday, edtPhone;
    private NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Thông tin tài khoản");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        tvName = findViewById(R.id.tvName);
        edtEmail = findViewById(R.id.edtEmail);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPhone = findViewById(R.id.edtPhone);




        nguoiDungDAO = new NguoiDungDAO(this);
        SharedPreferences preferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        final String strEmail = preferences.getString("email", "");

        edtEmail.setEnabled(false);
        edtBirthday.setEnabled(false);
        edtPhone.setEnabled(false);


        tvName.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getName()));
        edtEmail.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getEmail()));
        edtBirthday.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getBirthday()));
        edtPhone.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getPhone()));

    }


    public void btnDangXuat(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        //xoa tinh trang luu tru truoc do
        edit.clear();
        edit.commit();

        Intent i = new Intent(AccountActivity.this, MainActivity.class);  //your class
        startActivity(i);
        finish();
    }

    public void btnThaydoiThongtin(View view) {
        startActivity(new Intent(getApplicationContext(), AccountDetailsActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


}
