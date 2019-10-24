package com.xhp.testutils.business;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseActivity;

public class StartActivity extends BaseActivity {


    private TextView mDoSomething;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        mDoSomething = findViewById(R.id.do_something);
        mDoSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StartActivity.this, "click here", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(StartActivity.this,PlayerActivity.class));
            }
        });
        ViewTreeObserver viewTreeObserver = mDoSomething.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("test","width="+mDoSomething.getWidth()+" height="+mDoSomething.getHeight()+" Traslateionx="+mDoSomething.getTranslationX());
                Log.d("test","x="+mDoSomething.getX()+" y="+mDoSomething.getY()+" Traslateionx="+mDoSomething.getTranslationX());
                ValueAnimator animator = ValueAnimator.ofFloat(0, 180);
                animator.setDuration(2000);
                animator.setInterpolator(new TimeInterpolator() {
                    @Override
                    public float getInterpolation(float x) {
                        x =   x * x * (3 - 2 * x);
                        return x;
                    }
                });
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mDoSomething.setTranslationX((Float) animation.getAnimatedValue());
                    }
                });
                animator.start();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("test","width="+mDoSomething.getWidth()+" height="+mDoSomething.getHeight()+" Traslateionx="+mDoSomething.getTranslationX());
                        Log.d("test","x="+mDoSomething.getX()+" y="+mDoSomething.getY()+" Traslateionx="+mDoSomething.getTranslationX());
                    }
                }, 7000);
            }
        });
    }

    @Override
    protected void initData(Context context) {

    }


}
