package com.example.myfirstapp.ui.scan_bottle;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class ScanWineViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScanWineViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}