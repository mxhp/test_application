package com.xhp.testutils.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xhp.testutils.base.utils.ActivityUtils;
import com.xhp.testutils.base.utils.StatusBarUtil;
import com.xhp.testutils.contract.BaseContract;
import com.xhp.testutils.presenter.BasePresenter;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseContract.BaseView {

    private boolean showActionBar = false;

    private boolean immersibeEffect = false;

    public  P mPresenter ;


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
        mPresenter = createPresenter();
        if (mPresenter!=null) {
            mPresenter.attachView(this);
        }
        initView();
        initData(this);
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

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
        if (mPresenter!=null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        ActivityUtils.getInstance().remove(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int code, String errorMsg) {

    }

    @Override
    public void hideLoading(){

    }
}
