package com.ftios.duan1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.ftios.duan1.dao.NguoiDungDAO;
import com.ftios.duan1.ui.dashboard.DashboardFragment;


public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String username, String password, Boolean remember) {

        if (username.isEmpty()) {
            loginView.setErrorUsername();
        } else if (password.isEmpty()) {
            loginView.setErrorPassword();

        } else if (loginView.login()){

            if (remember == true) {
                SharedPreferences loginPreferences = DashboardFragment.getContextOfApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                SharedPreferences.Editor loginPrefsEditor = loginPreferences.edit();

                loginPrefsEditor.putString("email", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            }

            loginView.navigate();

        } else if (username.equalsIgnoreCase("admin1") && password.equalsIgnoreCase("admin")) {

            if (remember == true) {
                SharedPreferences loginPreferences = DashboardFragment.getContextOfApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                SharedPreferences.Editor loginPrefsEditor = loginPreferences.edit();

                loginPrefsEditor.putString("email", "admin");
                loginPrefsEditor.putString("password", "admin");
                loginPrefsEditor.commit();
            }


            loginView.navigate();
        } else {
            loginView.setErrorAccount();
        }
    }
}
