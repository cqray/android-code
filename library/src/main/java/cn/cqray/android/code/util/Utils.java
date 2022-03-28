package cn.cqray.android.code.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

public class Utils {

    private static volatile Application sApplication;

    public static void initialize(Application application) {
        ActivityUtils.initialize(application);
        sApplication = application;
    }

    public static Application getApp() {
        if (sApplication == null) {
            throw new RuntimeException("Please call Utils.initialize() first.");
        }
        return sApplication;
    }

    @NonNull
    public static Context getContext() {
        if (ActivityUtils.getCurrent() == null) {
            return getApp().getApplicationContext();
        } else {
            return ActivityUtils.getCurrent();
        }
    }

    public static Resources getResources() {
        return getContext().getResources();
    }
}
