package com.xhp.testutils.business;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.presenter.BasePresenter;

public class HomeActivity extends BaseActivity {

    private ViewPager mViewPage;
    private TextView mMusicBtnMusic;
    private TextView mMusicBtnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        mViewPage = findViewById(R.id.view_page);
        mMusicBtnMusic = findViewById(R.id.music_btn_music);
        mMusicBtnVideo = findViewById(R.id.music_btn_video);
    }

    @Override
    protected void initData(Context context) {

    }
}
