package cn.cqray.android.code.delegate;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import cn.cqray.android.code.graphics.RoundDrawable;
import cn.cqray.android.code.lifecycle.SimpleLiveData;
import cn.cqray.android.code.util.SizeUnit;
import cn.cqray.android.code.util.SizeUtils;
import cn.cqray.android.code.util.Utils;

/**
 * 控件相关操作委托
 * @author Cqray
 */
public class ViewDelegate<T extends View> {

    /** 圆角数量 **/
    private static final int RADII_LENGTH = 8;
    /** Fragment **/
    protected final Fragment mFragment;
    /** Activity **/
    protected final FragmentActivity mActivity;
    /** Handler句柄 **/
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    /** 圆角 **/
    protected final float[] mBackgroundRadii = new float[RADII_LENGTH];
    /** 间隔 **/
    protected final SimpleLiveData<float[]> mPadding = new SimpleLiveData<>();
    /** 显示 **/
    protected final SimpleLiveData<Integer> mVisibility = new SimpleLiveData<>();
    /** 高度 **/
    protected final SimpleLiveData<Float> mHeight = new SimpleLiveData<>();
    /** 高度 **/
    protected final SimpleLiveData<Float> mWidth = new SimpleLiveData<>();
    /** 背景图 **/
    protected final SimpleLiveData<Drawable> mBackground = new SimpleLiveData<>();
    /** 背景资源 **/
    protected final SimpleLiveData<Integer> mBackgroundResource = new SimpleLiveData<>();

    public ViewDelegate(Fragment fragment) {
        mFragment = fragment;
        mActivity = null;
        mHandler.post(() -> mFragment.getLifecycle().addObserver((LifecycleEventObserver) this::onStateChanged));
    }

    public ViewDelegate(FragmentActivity activity) {
        mFragment = null;
        mActivity = activity;
        mHandler.post(() -> mActivity.getLifecycle().addObserver((LifecycleEventObserver) this::onStateChanged));
    }

    protected void onStateChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {}

    @NonNull
    public LifecycleOwner getLifecycleOwner() {
        if (mFragment != null) {
            return mFragment;
        } else {
            assert mActivity != null;
            return mActivity;
        }
    }

    @NonNull
    public FragmentActivity requireActivity() {
        if (mFragment != null) {
            return mFragment.requireActivity();
        } else {
            assert mActivity != null;
            return mActivity;
        }
    }

    @NonNull
    public Context requireContext() {
        return requireActivity();
    }

    public void setView(T view) {
        if (view == null) {
            return;
        }
        LifecycleOwner owner = getLifecycleOwner();
        // 控件显示状态监听
        mVisibility.observe(owner, view::setVisibility);
        // 宽度变化监听
        mWidth.observe(owner, aFloat ->  {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                params = new ViewGroup.LayoutParams(aFloat.intValue(), -2);
            }
            params.width = aFloat.intValue();
            view.setLayoutParams(params);
        });
        // 高度变化监听
        mHeight.observe(owner, aFloat -> {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params == null) {
                params = new ViewGroup.LayoutParams(-2, aFloat.intValue());
            }
            params.height = aFloat.intValue();
            view.setLayoutParams(params);
        });
        // 设置背景变化监听
        mBackground.observe(owner, drawable -> {
            if (drawable == null) {
                // 不设置背景
                ViewCompat.setBackground(view, null);
            } else if (drawable instanceof ColorDrawable) {
                // 纯色背景设置圆角
                int color = ((ColorDrawable) drawable).getColor();
                GradientDrawable background = new GradientDrawable();
                background.setColor(color);
                background.setCornerRadii(mBackgroundRadii);
                ViewCompat.setBackground(view, background);
            } else {
                // 图片背景设置圆角
                RoundDrawable background = new RoundDrawable(drawable);
                background.setRadii(mBackgroundRadii, SizeUnit.PX);
                ViewCompat.setBackground(view, background);
            }
        });
        // 设置背景资源变化监听
        mBackgroundResource.observe(owner, aInt -> {
            Drawable drawable = ContextCompat.getDrawable(Utils.getContext(), aInt);
            mBackground.setValue(drawable);
        });
    }

    public void setPadding(float left, float top, float right, float bottom) {
        setPadding(left, top, right, bottom, SizeUnit.DP);
    }

    public void setPadding(float left, float top, float right, float bottom, SizeUnit unit) {
        float[] array = new float[4];
        array[0] = SizeUtils.applyDimension(left, unit);
        array[1] = SizeUtils.applyDimension(top, unit);
        array[2] = SizeUtils.applyDimension(right, unit);
        array[3] = SizeUtils.applyDimension(bottom, unit);
        mPadding.setValue(array);
    }

    public void setPadding(float padding) {
        setPadding(padding, SizeUnit.DP);
    }

    public void setPadding(float padding, SizeUnit unit) {
        float[] array = new float[4];
        for (int i = 0; i < array.length; i++) {
            array[i] = SizeUtils.applyDimension(padding, unit);
        }
        mPadding.setValue(array);
    }

    /**
     * 设置控件宽度
     * <p>默认单位DP</p>
     * @param width 宽度
     */
    public void setWidth(float width) {
        float size = SizeUtils.applyDimension(width, SizeUnit.DP);
        mWidth.setValue(size);
    }

    /**
     * 设置控件宽度
     * @param width 宽度
     * @param unit 值单位
     */
    public void setWidth(float width, SizeUnit unit) {
        float size = SizeUtils.applyDimension(width, unit);
        mWidth.setValue(size);
    }

    /**
     * 设置控件高度
     * <p>默认单位DP</p>
     * @param height 高度
     */
    public void setHeight(float height) {
        float size = SizeUtils.applyDimension(height, SizeUnit.DP);
        mHeight.setValue(size);
    }

    /**
     * 设置控件高度
     * @param height 高度
     * @param unit 值单位
     */
    public void setHeight(float height, SizeUnit unit) {
        float size = SizeUtils.applyDimension(height, unit);
        mHeight.setValue(size);
    }

    /**
     * 设置控件显示
     * @param visible 是否显示
     */
    public void setVisible(boolean visible) {
        mVisibility.setValue(visible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 设置控件隐藏
     * @param gone 是否隐藏
     */
    public void setGone(boolean gone) {
        mVisibility.setValue(gone ? View.GONE : View.VISIBLE);
    }

    /**
     * 设置控件显示状态
     * @param visibility 显示状态
     */
    public void setVisibility(int visibility) {
        mVisibility.setValue(visibility);
    }

    /**
     * 设置圆角大小，每个圆角都有两个半径值[X，Y]。圆角按左上、右上、右下、左下排列
     * <p>默认单位DP</p>
     * @param radii 8个值的数组，4对[X，Y]半径
     */
    public void setRadii(float [] radii) {
        setRadii(radii, SizeUnit.DP);
    }

    /**
     * 设置圆角大小，每个圆角都有两个半径值[X，Y]。圆角按左上、右上、右下、左下排列
     * @param radii 8个值的数组，4对[X，Y]半径
     * @param unit  值单位
     */
    public void setRadii(float [] radii, SizeUnit unit) {
        if (radii == null || radii.length < RADII_LENGTH) {
            throw new IllegalArgumentException("Radii array length must >= " + RADII_LENGTH);
        }
        for (int i = 0; i < RADII_LENGTH; i++) {
            mBackgroundRadii[i] = SizeUtils.applyDimension(radii[i], unit);
        }
        mBackground.setValue(mBackground.getValue());
    }

    /**
     * 设置圆角大小
     * <p>默认单位DP</p>
     * @param radius 圆角半径
     */
    public void setRadius(float radius) {
        setRadius(radius, SizeUnit.DP);
    }

    /**
     * 设置圆角大小
     * @param radius 圆角半径
     * @param unit 值单位
     */
    public void setRadius(float radius, SizeUnit unit) {
        float [] radii = new float[RADII_LENGTH];
        for (int i = 0; i < RADII_LENGTH; i++) {
            radii[i] = radius;
        }
        setRadii(radii, unit);
    }

    public void setBackground(Drawable drawable) {
        mBackground.setValue(drawable);
    }

    public void setBackgroundColor(@ColorInt int color) {
        mBackground.setValue(new ColorDrawable(color));
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        mBackgroundResource.setValue(resId);
    }

}
