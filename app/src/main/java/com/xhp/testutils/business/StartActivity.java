package com.xhp.testutils.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.xhp.testutils.MainActivity;
import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.contract.StartContract;
import com.xhp.testutils.presenter.StartPresenter;

public class StartActivity extends BaseActivity<StartPresenter> implements StartContract.View {


    private ImageView mDoSomething;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected StartPresenter createPresenter() {
        return new StartPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initView() {
        mDoSomething = findViewById(R.id.do_something);
    }

    @Override
    protected void initData(Context context) {
        mPresenter.getImages();
    }


    @Override
    public void showStartImage(String string) {
        Glide.with(getBaseContext()).load(string).listener(new RequestListener(){

            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
//                gotoMain();
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
//                gotoMain();
                return false;
            }
        }).into(mDoSomething);
    }

    @Override
    public void gotoMain() {
        Intent intent =new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(int code, String errorMsg) {
//        super.showError(code, errorMsg);
        Toast.makeText(getBaseContext(),errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    public void gotoMain(View view) {
        gotoMain();
    }
}
