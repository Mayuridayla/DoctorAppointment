package com.test.myapplication.ui.live;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Live fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}