package com.xhp.testutils.util;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.view.View;

public class AnimatorUtils {
    /*
     * 控件通过属性动画相对于自身中心点缩放
     * @param context
     * @param view 需要缩放的控件
     * @param animId 属性动画xml文件
     */
    public static Animator scaleRelCenter(Context context, View view, int animId){
        Animator animator = AnimatorInflater.loadAnimator(context, animId);
        view.setPivotX(view.getMeasuredWidth()/2f);
        view.setPivotY(view.getMeasuredHeight()/2f);
        //view.invalidate();
        animator.setTarget(view);
        animator.start();
        return animator;
    }
}
