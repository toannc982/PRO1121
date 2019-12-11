package com.ftios.duan1.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ftios.duan1.LoginPresenter;
import com.ftios.duan1.LoginView;
import com.ftios.duan1.MainActivity;
import com.ftios.duan1.R;
import com.ftios.duan1.dao.NguoiDungDAO;
import com.ftios.duan1.model.Account;

import java.util.List;

public class DashboardFragment extends Fragment implements LoginView {

    private DashboardViewModel dashboardViewModel;

    View root = null;

    private TextView tvName;
    private EditText edtEmail, edtPassword, edtBirthday, edtPhone;
    private CheckBox chbRemember;

    private Button sendBtn;
    private LoginPresenter loginPresenter;

    public static Context contextOfApplication;

    private NguoiDungDAO nguoiDungDAO;

    private List<Account> account;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        loginPresenter = new LoginPresenter(this);

        contextOfApplication = getContext();

        nguoiDungDAO = new NguoiDungDAO(this.getActivity());


        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);


        SharedPreferences preferences = this.getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);

        final String strEmail = preferences.getString("email", "");
        final String strPassword = preferences.getString("password", "");

        if (strEmail.isEmpty() && strPassword.isEmpty()) {
            root = inflater.inflate(R.layout.fragment_dashboard, container, false);

            edtEmail = root.findViewById(R.id.edtEmail);
            edtPassword = root.findViewById(R.id.edtPassword);
//            chbRemember = root.findViewById(R.id.chbRemember);

            sendBtn = (Button) root.findViewById(R.id.btnLogin);


            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Toast.makeText(getActivity(), edtEmail.getText(), Toast.LENGTH_SHORT).show();

                    loginPresenter.login(edtEmail.getText().toString(), edtPassword.getText().toString(), true);
                }
            });

        } else {
            root = inflater.inflate(R.layout.activity_account, container, false);
            tvName = root.findViewById(R.id.tvName);
            edtEmail = root.findViewById(R.id.edtEmail);
            edtBirthday = root.findViewById(R.id.edtBirthday);
            edtPhone = root.findViewById(R.id.edtPhone);
            edtEmail.setEnabled(false);
            edtBirthday.setEnabled(false);
            edtPhone.setEnabled(false);


            tvName.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getName()));
            edtEmail.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getEmail()));
            edtBirthday.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getBirthday()));
            edtPhone.setText(String.valueOf(nguoiDungDAO.searchUser(strEmail).getPhone()));
        }


//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });



        return root;
    }


    @Override
    public boolean login() {

        Boolean check;

        if (nguoiDungDAO.checkLogin(edtEmail.getText().toString(), edtPassword.getText().toString())){

            check = true;

        } else {
            check = false;
        }

        return check;

    }

    @Override
    public void setErrorUsername() {

        edtEmail.setError("Chưa nhập Email");

    }

    @Override
    public void setErrorPassword() {
        edtPassword.setError("Chưa nhập Mật khẩu");
    }

    @Override
    public void setErrorAccount() {
        edtPassword.setText("");
        showToast("Tài khoản không tồn tại. Thử lại");
    }

    @Override
    public void navigate() {

        Intent i = new Intent(contextOfApplication, MainActivity.class);  //your class
        startActivity(i);
        this.getActivity().finish();

    }

    @Override
    public void rememberPassword() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    public void showToast(String mgs) {
        Toast.makeText(contextOfApplication, mgs, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onResume() {
//
//        super.onResume();
//        SharedPreferences preferences = this.getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
//
//        final String strUserName = preferences.getString("REMEMBER", "");
//
//
//        Toast.makeText(this.getActivity(), strUserName, Toast.LENGTH_SHORT).show();
//
//    }
}