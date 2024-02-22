package com.example.myfirstapp.ui.list_wine;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myfirstapp.ListWineActivity;

public class ListWineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListWineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Welcome "+ ListWineActivity.username +", to your wine cellar");
    }

    public LiveData<String> getText() {
        return mText;
    }
}