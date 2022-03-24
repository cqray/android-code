package cn.cqray.android.util;

import android.content.res.Resources;

import androidx.annotation.DimenRes;

/**
 * 尺寸工具类
 * @author Cqray
 */
public class DimenUtils {

    private DimenUtils() {}

    public static int dp2px(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    public static float getSize(SizeUnit unit, @DimenRes int redId) {
        if (unit == SizeUnit.PX) {

        } else {

        }
        return 0;
    }
}
