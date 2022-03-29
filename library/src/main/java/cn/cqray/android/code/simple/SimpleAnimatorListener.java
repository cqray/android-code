package cn.cqray.android.code.simple;

import android.animation.Animator;

public interface SimpleAnimatorListener extends Animator.AnimatorListener {

    @Override
    default void onAnimationStart(Animator animation) {}

    @Override
    default void onAnimationCancel(Animator animation) {}

    @Override
    default void onAnimationRepeat(Animator animation) {}
}
