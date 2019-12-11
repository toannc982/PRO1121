package com.ftios.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ftios.duan1.dao.NguoiDungDAO;
import com.ftios.duan1.model.Account;

public class SignupActivity extends AppCompatActivity {


    private EditText edtSignupEmail, edtSignupName, edtSignupPass, edtSignupRePass;


    private NguoiDungDAO objNguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng kí tài khoản");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DB4437")));

        edtSignupEmail = findViewById(R.id.edtSignupEmail);
        edtSignupName = findViewById(R.id.edtSignupName);
        edtSignupPass = findViewById(R.id.edtSignupPass);
        edtSignupRePass = findViewById(R.id.edtSignupRePass);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    public void kiemtraDangki(View view) {

        objNguoiDungDAO = new NguoiDungDAO(SignupActivity.this);

        String email = edtSignupEmail.getText().toString();
        String name = edtSignupName.getText().toString();

        String password = edtSignupPass.getText().toString();
        String repassword = edtSignupRePass.getText().toString();

        Account nguoiDung = new Account(name, email, name, password, "", "");

        boolean isInsertTrue = objNguoiDungDAO.insertNguoiDung(nguoiDung);

        if (isInsertTrue) {
            Toast.makeText(getApplicationContext(), "Them Thanh Cong", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Them That Bai", Toast.LENGTH_SHORT).show();
        }

    }
}
