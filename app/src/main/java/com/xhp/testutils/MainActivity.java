package com.xhp.testutils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLive;
    private Button mVod;
    private Button mCategory;
    private Button mDetail;
    private Button mLookback;
    private Button mTimeshift;
    private Button mCategory1;
    private Button mCategory2;
    private Button mDetail1;
    private Button mDetail2;
    private EditText mDetailPram;
    private EditText mCategoryPram;
    private String detail_prammter;
    private String category_prammter;
    private TextView mInfo;
    private Button mStartapp;
    private MyHandler myHandler = new MyHandler(this);
    private Button mShanxi;
    private Button mHuacheng;
    private Button mSuixinkan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mLive = findViewById(R.id.live);
        mVod = findViewById(R.id.vod);
        mCategory = findViewById(R.id.category);
        mDetail = findViewById(R.id.detail);
        mLookback = findViewById(R.id.lookback);
        mTimeshift = findViewById(R.id.timeshift);
        mLive.setOnClickListener(this);
        mCategory.setOnClickListener(this);
        mDetail.setOnClickListener(this);
        mLookback.setOnClickListener(this);
        mTimeshift.setOnClickListener(this);
        mVod.setOnClickListener(this);
        mCategory1 = findViewById(R.id.category_1);
        mCategory2 = findViewById(R.id.category_2);
        mDetail1 = findViewById(R.id.detail_1);
        mDetail2 = findViewById(R.id.detail_2);
        mDetailPram = findViewById(R.id.detail_pram);
        mCategoryPram = findViewById(R.id.category_pram);

        mCategory1.setOnClickListener(this);
        mCategory2.setOnClickListener(this);
        mDetail1.setOnClickListener(this);
        mDetail2.setOnClickListener(this);
        mInfo = findViewById(R.id.info);
        mInfo.setText("" +
                "" + Environment.getExternalStorageDirectory().getPath() + "/n" + "getFilesDir" + this.getFilesDir().getPath());
        mStartapp = findViewById(R.id.startApp);
        mStartapp.setOnClickListener(this);
        mShanxi = findViewById(R.id.shanxi);
        mHuacheng = findViewById(R.id.huacheng);
        mSuixinkan = findViewById(R.id.suixinkan);
        mShanxi.setOnClickListener(this);
        mHuacheng.setOnClickListener(this);
        mSuixinkan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startApp:
                Intent startApp = new Intent();
                startApp.setClassName("com.sumavision.ivideoforstb.shanxi",
                        "com.sumavision.ivideoforstb.activity.RouteActivity");
                startApp.putExtra("type", 5);
                startApp.putExtra("categoryId", "");
                startApp.putExtra("categoryName", "优点专区");
                startActivity(startApp);
                break;
            case R.id.shanxi:
                Intent shanxi = new Intent();
                shanxi.setClassName("com.sumavision.ivideoforstb.shanxi",
                        "com.sumavision.ivideoforstb.activity.RouteActivity");
                shanxi.putExtra("type", 5);
                shanxi.putExtra("categoryId", "");
                shanxi.putExtra("categoryName", "");
                startActivity(shanxi);
                break;
            case R.id.huacheng:
                Intent huacheng = new Intent();
                huacheng.setClassName("com.sumavision.ivideoforstb.shanxi",
                        "com.sumavision.ivideoforstb.activity.RouteActivity");
                huacheng.putExtra("type", 5);
                huacheng.putExtra("categoryId", "d50f1539f67adf5a");
                huacheng.putExtra("categoryName", "华城专区");
                startActivity(huacheng);
                break;
            case R.id.suixinkan:
                Intent suixinkan = new Intent();
                suixinkan.setClassName("com.sumavision.ivideoforstb.shanxi",
                        "com.sumavision.ivideoforstb.activity.RouteActivity");
                suixinkan.putExtra("type", 5);
                suixinkan.putExtra("categoryId", "3c4d80aa3f0b2454");
                suixinkan.putExtra("categoryName", "随心看专区");
                startActivity(suixinkan);
                break;
            case R.id.live:
                Intent liveintent = new Intent();
                liveintent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                liveintent.putExtra("type", 1);
                liveintent.putExtra("channelId", "1001");
                startActivity(liveintent);
                break;
            case R.id.vod:
                Intent vodintent = new Intent();
                vodintent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                vodintent.putExtra("type", 2);
                vodintent.putExtra("programId", "001");
                startActivity(vodintent);
                break;
            case R.id.category:
                if (TextUtils.isEmpty(category_prammter)) {
                    Toast.makeText(this, "categoryId为空!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent categoryintent = new Intent();
                    categoryintent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                    categoryintent.putExtra("type", 5);
                    categoryintent.putExtra("categoryId", category_prammter);
                    categoryintent.putExtra("categoryName", "");
                    categoryintent.putExtra("secCategoryId", "");
                    categoryintent.putExtra("linkValue", "");
                    startActivity(categoryintent);
                }
                break;
            case R.id.detail:
                if (TextUtils.isEmpty(detail_prammter)) {
                    Toast.makeText(this, "programId为空!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                    intent.putExtra("type", 6);
                    intent.putExtra("programId", detail_prammter);
                    intent.putExtra("programType", "");
                    startActivity(intent);
                }
                break;
            case R.id.timeshift:
                Intent timeshiftintent = new Intent();
                timeshiftintent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                timeshiftintent.putExtra("type", 4);
                timeshiftintent.putExtra("freq", "11");
                timeshiftintent.putExtra("programNumber", "11");
                timeshiftintent.putExtra("absoluteTime", "20190909090900");
                startActivity(timeshiftintent);

                break;
            case R.id.lookback:
                Intent backintent = new Intent();
                backintent.setClassName("com.sumavision.ivideoforstb.sichuan.prefecture", "com.sumavision.ivideoforstb.activity.RouteActivity");
                backintent.putExtra("type", 3);
                backintent.putExtra("freq", "11");
                backintent.putExtra("programNumber", "11");
                backintent.putExtra("startTime", "20190917190000");
                startActivity(backintent);
                break;
            case R.id.category_1:
                category_prammter = "ef00a9d1a470e394";
                mCategoryPram.setText(category_prammter);
                break;
            case R.id.category_2:
                category_prammter = "424d8218e3afc106";
                mCategoryPram.setText(category_prammter);
                break;
            case R.id.detail_1:
                detail_prammter = "YinShengSouHu_190228162212580o8000_package";
                mDetailPram.setText(detail_prammter);
                break;
            case R.id.detail_2:
                detail_prammter = "Wenguang_19051516454979245000_package";
                mDetailPram.setText(detail_prammter);
                break;
        }
    }

    /**
     * 防止内存泄漏的handler对象
     */
    private static class MyHandler extends Handler {
        private WeakReference<Context> contextWeakReference;

        public MyHandler(Context context) {
            this.contextWeakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
