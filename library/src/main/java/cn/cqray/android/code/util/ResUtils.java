package cn.cqray.android.code.util;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

public class ResUtils {

    public static int getIdByName(String name) {
        return getIdByName(name, "id");
    }

    public static int getStringIdByName(String name) {
        return getIdByName(name, "string");
    }

    public static int getColorIdByName(String name) {
        return getIdByName(name, "color");
    }

    public static int getDimenIdByName(String name) {
        return getIdByName(name, "dimen");
    }

    public static int getIdByName(String name, String defType) {
        String packageName = Utils.getContext().getPackageName();
        return Utils.getContext().getResources().getIdentifier(name, defType, packageName);
    }

    public static int dp2px(float dp) {
        float density = Utils.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }

    public static float px2dp(int px) {
        float density = Utils.getResources().getDisplayMetrics().density;
        return px / density;
    }

    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(Utils.getContext(), resId);
    }
}
