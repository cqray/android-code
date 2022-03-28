package cn.cqray.android.code.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DimenRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager2.widget.ViewPager2;

/**
 * 控件工具类
 * @author Cqray
 */
public class ViewUtils {

    private ViewUtils() {}

    /**
     * 通过View获取Activity
     * @param view 控件
     * @return Activity
     */
    @Nullable
    public static Activity getActivity(@NonNull View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * 渲染界面
     * @param resId 界面资源ID
     * @return 界面
     */
    public static View inflate(@LayoutRes int resId) {
        Activity act = ActivityUtils.requireCurrent();
        ViewGroup root = act.findViewById(android.R.id.content);
        return LayoutInflater.from(act).inflate(resId, root, false);
    }

    /**
     * 设置OverScrollMode属性
     * @param view 控件
     * @param overScrollMode OverScrollMode属性
     */
    public static void setOverScrollMode(View view, int overScrollMode) {
        try {
            if (view instanceof ViewPager2) {
                // ViewPager2需要特殊处理
                View child = ((ViewPager2) view).getChildAt(0);
                if (child instanceof RecyclerView) {
                    child.setOverScrollMode(overScrollMode);
                }
            } else if (view != null) {
                // 其他控件常规处理
                view.setOverScrollMode(overScrollMode);
            }
        } catch (Exception ignore) {}
    }

    /**
     * 设置控件外部间隔
     * @param view 控件
     * @param unit 单位
     * @param left 左
     * @param top 上
     * @param right 右
     * @param bottom 下
     */
    public static void setMargin(View view, SizeUnit unit, float left, float top, float right, float bottom) {
        if (view != null) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params = params == null ? new ViewGroup.MarginLayoutParams(-2, -2) : params;
            if (unit == SizeUnit.PX) {
                params.setMargins((int) left, (int) top, (int) right, (int) bottom);
            } else {
                params.leftMargin = SizeUtils.dp2px(left);
                params.topMargin = SizeUtils.dp2px(top);
                params.rightMargin = SizeUtils.dp2px(right);
                params.bottomMargin = SizeUtils.dp2px(bottom);
            }
        }
    }

    /**
     * 设置控件外部间隔（默认DP）
     * @param view 控件
     * @param left 左
     * @param top 上
     * @param right 右
     * @param bottom 下
     */
    public static void setMargin(View view, float left, float top, float right, float bottom) {
        setMargin(view, SizeUnit.DP, left, top, right, bottom);
    }

    /**
     * 设置控件外部间隔
     * @param view 控件
     * @param unit 单位
     * @param margin 间隔
     */
    public static void setMargin(View view, SizeUnit unit, float margin) {
        setMargin(view, unit, margin, margin, margin, margin);
    }

    /**
     * 设置控件外部间隔（默认DP）
     * @param view 控件
     * @param margin 间隔
     */
    public static void setMargin(View view, float margin) {
        setMargin(view, SizeUnit.DP, margin, margin, margin, margin);
    }

    /**
     * 设置控件外部间隔（默认DP）
     * @param view 控件
     * @param resId 资源ID
     */
    public static void setMarginRes(View view, @DimenRes int resId) {
        float margin = SizeUtils.get(resId, SizeUnit.PX);
        setMargin(view, SizeUnit.PX, margin);
    }

    /**
     * 关闭RecyclerView动画
     * @param rv RecyclerView控件
     */
    public static void closeRecyclerViewAnimator(RecyclerView rv) {
        if (rv != null) {
            RecyclerView.ItemAnimator itemAnimator = rv.getItemAnimator();
            if (itemAnimator != null) {
                // 设置各种动画时间为零
                itemAnimator.setRemoveDuration(0);
                itemAnimator.setMoveDuration(0);
                itemAnimator.setChangeDuration(0);
                itemAnimator.setAddDuration(0);
            }
            if (itemAnimator instanceof SimpleItemAnimator) {
                // 设置不支持动画
                ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            }
        }
    }
}
