package com.ftios.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ftios.duan1.dao.NguoiDungDAO;
import com.ftios.duan1.model.Account;

public class AccountDetailsActivity extends AppCompatActivity {

    private EditText edtName, edtBirthday, edtPhone;
    private TextView tvName;
    private NguoiDungDAO nguoiDungDAO;

    private String strEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thay đổi thông tin");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));


        tvName = findViewById(R.id.tvName);
        edtName = findViewById(R.id.edtName);
        edtBirthday = findViewById(R.id.edtBirthday);
        edtPhone = findViewById(R.id.edtPhone);

        nguoiDungDAO = new NguoiDungDAO(this);
        SharedPreferences preferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        strEmail = preferences.getString("email", "");


        tvName.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getName()));
        edtName.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getName()));
        edtBirthday.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getBirthday()));
        edtPhone.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getPhone()));
    }

    public void btnCapnhatThongtin(View view) {

        nguoiDungDAO = new NguoiDungDAO(this);

        String name = edtName.getText().toString();
        String birthday = edtBirthday.getText().toString();

        String phone = edtPhone.getText().toString();



        boolean isInsertTrue = nguoiDungDAO.updateInfoNguoiDung(strEmail, name, birthday, phone);

        if (isInsertTrue) {
            Toast.makeText(getApplicationContext(), "Sửa Thanh Cong", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Sửa That Bai", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
