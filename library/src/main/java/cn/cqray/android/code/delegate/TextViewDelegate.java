package cn.cqray.android.code.delegate;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;

import cn.cqray.android.code.lifecycle.SimpleLiveData;
import cn.cqray.android.code.util.SizeUnit;
import cn.cqray.android.code.util.SizeUtils;

public class TextViewDelegate extends ViewDelegate<TextView> {

    /** 文本 **/
    protected final SimpleLiveData<CharSequence> mText = new SimpleLiveData<>();
    /** 文本资源 **/
    protected final SimpleLiveData<Integer> mTextRes = new SimpleLiveData<>();
    /** 文本颜色 **/
    protected final SimpleLiveData<ColorStateList> mTextColor = new SimpleLiveData<>();
    /** 文本大小 **/
    protected final SimpleLiveData<Integer> mTextSize = new SimpleLiveData<>();
    /** 文本加粗 **/
    protected final SimpleLiveData<Integer> mTextStyle = new SimpleLiveData<>();
    /** 文本位置 **/
    protected final SimpleLiveData<Integer> mGravity = new SimpleLiveData<>();

    public TextViewDelegate(Fragment fragment) {
        super(fragment);
    }

    public TextViewDelegate(FragmentActivity activity) {
        super(activity);
    }

    @Override
    protected void onCreate(@NonNull LifecycleOwner owner) {
        super.onCreate(owner);
        // 设置文本变化监听
        mText.observe(owner, charSequence -> post(() -> requireView().setText(charSequence)));
        mTextRes.observe(owner, aInt -> post(() -> requireView().setText(aInt)));
        // 设置文本颜色变化监听
        mTextColor.observe(owner, colorStateList -> post(() -> requireView().setTextColor(colorStateList)));
        // 设置文本大小变化监听
        mTextSize.observe(owner, aInt -> post(() -> requireView().setTextSize(TypedValue.COMPLEX_UNIT_PX, aInt)));
        // 设置文本样式变化监听
        mTextStyle.observe(owner, aBoolean -> post(() -> requireView().setTypeface(Typeface.defaultFromStyle(aBoolean))));
        // 设置文本位置变化监听
        mGravity.observe(owner, aInt -> post(() -> requireView().setGravity(aInt)));
    }

    public void setText(@StringRes int resId) {
        mTextRes.setValue(resId);
    }

    public void setText(CharSequence content) {
        mText.setValue(content);
    }

    public void setTextColor(@ColorInt int color) {
        mTextColor.setValue(ColorStateList.valueOf(color));
    }

    public void setTextColor(String color) {
        mTextColor.setValue(ColorStateList.valueOf(Color.parseColor(color)));
    }

    public void setTextColor(ColorStateList colorStateList) {
        mTextColor.setValue(colorStateList);
    }

    public void setTextSize(float size) {
        mTextSize.setValue((int) SizeUtils.applyDimension(size, SizeUnit.DP));
    }

    public void setTextSize(float size, SizeUnit unit) {
        mTextSize.setValue((int) SizeUtils.applyDimension(size, unit));
    }

    public void setTextBold(boolean bold) {
        mTextStyle.setValue(bold ? Typeface.BOLD : Typeface.NORMAL);
    }

    public void setTextStyle(int style) {
        mTextStyle.setValue(style);
    }

    public void setGravity(int gravity) {
        mGravity.setValue(gravity);
    }
}
