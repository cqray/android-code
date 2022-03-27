package cn.cqray.android.util.dd;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.view.View;

import androidx.annotation.ColorInt;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import cn.cqray.android.util.SizeUnit;
import cn.cqray.android.util.SizeUtils;
import cn.cqray.android.util.Utils;

public class ViewDelegate<T extends View>  {


    /** 圆角数量 **/
    private static final int RADII_LENGTH = 8;
    /** 圆角 **/
    private float[] mBackgroundRadii;

    private final SimpleLiveData<T> mView = new SimpleLiveData<>();
    /** 间隔 **/
    private final SimpleLiveData<float[]> mPadding = new SimpleLiveData<>();
    /** 显示 **/
    private final SimpleLiveData<Integer> mVisibility = new SimpleLiveData<>();
    /** 高度 **/
    private final SimpleLiveData<Float> mHeight = new SimpleLiveData<>();
    /** 高度 **/
    private final SimpleLiveData<Float> mWidth = new SimpleLiveData<>();
    /** 背景图 **/
    private final SimpleLiveData<Drawable> mBackground = new SimpleLiveData<>();
    /** 背景资源 **/
    private final SimpleLiveData<Integer> mBackgroundRes = new SimpleLiveData<>();

    public void setWidth(float width) {
        float size = SizeUtils.applyDimension(width, SizeUnit.DP);
        mWidth.setValue(size);
    }

    public void setWidth(float width, SizeUnit unit) {
        float size = SizeUtils.applyDimension(width, unit);
        mWidth.setValue(size);
    }

    public void setHeight(float height) {
        float size = SizeUtils.applyDimension(height, SizeUnit.DP);
        mHeight.setValue(size);
    }

    public void setHeight(float height, SizeUnit unit) {
        float size = SizeUtils.applyDimension(height, unit);
        mHeight.setValue(size);
    }

    public void setVisibility(int gravity) {
        mVisibility.setValue(gravity);
    }

    public void setBackground(Drawable drawable) {
        mBackground.setValue(drawable);

        GradientDrawable drawable1 = new GradientDrawable();

    }

    public void setBackgroundColor(@ColorInt int color) {
        mBackground.setValue(new ColorDrawable(color));
    }

    private void requestBackground(Drawable drawable) {
        if (drawable == null) {
            mBackground.postValue(null);
            return;
        }
        if (mBackgroundRadii == null) {
            mBackground.postValue(drawable);
            return;
        }
        // 是否所有的软胶大小都相等
        boolean allRadiusEqual = true;
        float value = mBackgroundRadii[0];
        for (float v : mBackgroundRadii) {
            if (Math.abs(v - value) < 0.01f) {
                allRadiusEqual = false;
                break;
            }
        }
        if (allRadiusEqual) {
            Bitmap bitmap = Bitmap.createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            drawable.draw(canvas);

            RoundedBitmapDrawable newDrawable = RoundedBitmapDrawableFactory.create(Utils.getResources(), bitmap);
            newDrawable.setCornerRadius(value);
            newDrawable.setAntiAlias(true);
            mBackground.postValue(newDrawable);
        } else {
            mBackground.postValue(drawable);
        }
    }
}
