package cn.cqray.android.code.lifecycle;

import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

/**
 * 简单LiveData
 * <p>将setValue()、postValue()改为一样的实现。</p>
 * <p>在主线程中，直接执行。其他线程，跳转线程再执行。</p>
 * @author Cqray
 */
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
