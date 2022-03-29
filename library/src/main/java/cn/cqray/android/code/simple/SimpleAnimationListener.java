package cn.cqray.android.code.simple;

import android.view.animation.Animation;

public interface SimpleAnimationListener extends Animation.AnimationListener {

    @Override
    default void onAnimationStart(Animation animation) {}

    @Override
    default void onAnimationRepeat(Animation animation) {}
}
