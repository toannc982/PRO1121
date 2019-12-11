package com.ftios.duan1;

public interface LoginView {

    boolean login();

    void setErrorUsername();

    void setErrorPassword();

    void setErrorAccount();

    void navigate();

    void rememberPassword();

    void showLoading();

    void hideLoading();


}
