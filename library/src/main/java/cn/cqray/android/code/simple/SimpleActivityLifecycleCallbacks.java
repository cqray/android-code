package cn.cqray.android.code.simple;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

public interface SimpleActivityLifecycleCallbacks extends Application.ActivityLifecycleCallbacks {

    @Override
    default void onActivityStarted(@NonNull Activity activity) {}

    @Override
    default void onActivityResumed(@NonNull Activity activity) {}

    @Override
    default void onActivityPaused(@NonNull Activity activity) {}

    @Override
    default void onActivityStopped(@NonNull Activity activity) {}

    @Override
    default void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

}
