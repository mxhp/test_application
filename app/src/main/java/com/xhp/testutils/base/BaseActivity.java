package com.xhp.testutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xhp.testutils.base.utils.ActivityUtils;
import com.xhp.testutils.base.utils.StatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean showActionBar = false;

    private boolean immersibeEffect = false;

    public void setShowActionBar(boolean showActionBar) {
        this.showActionBar = showActionBar;
    }

    public void setImmersibe(boolean immersibe) {
        this.immersibeEffect = immersibe;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.getInstance().addActivity(this);
        setContentView(getLayoutId());
        if (!showActionBar && getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        if (immersibeEffect) {
            StatusBarUtil.immersive(this);
        }
        initView(this);
        initData(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initView(Context context);

    protected abstract void initData(Context context);


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getInstance().remove(this);
    }
}
