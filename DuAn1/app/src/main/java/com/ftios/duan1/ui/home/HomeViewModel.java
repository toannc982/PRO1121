package com.ftios.duan1.ui.home;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ftios.duan1.AccountActivity;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        // mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}