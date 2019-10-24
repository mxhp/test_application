package com.xhp.testutils.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.contract.StartContract;

public class StartActivity extends BaseActivity implements StartContract.StartView {


    private ImageView mDoSomething;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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

    }

    @Override
    protected void initData(Context context) {

    }


    @Override
    public void doSomething() {

    }
}
