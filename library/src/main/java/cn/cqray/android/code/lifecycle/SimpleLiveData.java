package cn.cqray.android.code.lifecycle;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

public class SimpleLiveData<T> extends MutableLiveData<T> {

    @Override
    public void setValue(T value) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.setValue(value);
        } else {
            super.postValue(value);
        }
    }

    @Override
    public void postValue(T value) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.setValue(value);
        } else {
            super.postValue(value);
        }
    }
}
