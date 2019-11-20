package com.xhp.testutils.business;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xhp.testutils.MainActivity;
import com.xhp.testutils.R;
import com.xhp.testutils.TestActivity;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.contract.StartContract;
import com.xhp.testutils.presenter.StartPresenter;
import com.xhp.testutils.util.SharedPreferencesManager;

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
        SharedPreferencesManager.getInstance(getBaseContext()).
                putString(SharedPreferencesManager.SAVE_URL, string);
        Glide.with(getBaseContext()).load(string).into(mDoSomething);
    }

    @Override
    public void gotoMain() {
        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
        Bundle bundle = new Bundle();
        startActivity(intent);
        finish();
    }

    @Override
    public void showError(int code, String errorMsg) {
//        super.showError(code, errorMsg);
        Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }

    public void gotoMain(View view) {
        gotoMain();
    }
}
