package com.xhp.testutils.business;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.xhp.testutils.R;
import com.xhp.testutils.adapter.HomeViewPager;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.fragment.KaiyanFragment;
import com.xhp.testutils.fragment.UgankFragment;
import com.xhp.testutils.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private ViewPager mViewPage;
    private TextView mGank;
    private TextView mKaiyan;
    private List<Fragment> fragments;
    private UgankFragment Ugank;
    private KaiyanFragment Kaiyan;
    private HomeViewPager homeViewPager;

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
        mGank = findViewById(R.id.music_btn_music);
        mKaiyan = findViewById(R.id.music_btn_video);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.music_btn_music:
                        mViewPage.setCurrentItem(0);
                        mGank.setSelected(true);
                        mKaiyan.setSelected(false);
                        break;
                    case R.id.music_btn_video:
                        mViewPage.setCurrentItem(1);
                        mGank.setSelected(false);
                        mKaiyan.setSelected(true);
                        break;
                }
            }
        };
        mKaiyan.setOnClickListener(listener);
        mGank.setOnClickListener(listener);
        mViewPage.setOffscreenPageLimit(1);
        mViewPage.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mViewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    mKaiyan.setSelected(false);
                    mGank.setSelected(true);
                } else if (1 == position) {
                    mGank.setSelected(false);
                    mKaiyan.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void initData(Context context) {
        if (fragments == null) {
            fragments = new ArrayList<>();
            Ugank = new UgankFragment();
            Kaiyan = new KaiyanFragment();
            fragments.add(Ugank);
            fragments.add(Kaiyan);
        }
        if (homeViewPager == null) {
            homeViewPager = new HomeViewPager(getSupportFragmentManager());
        }
        homeViewPager.setFragmentList(fragments);
        mViewPage.setAdapter(homeViewPager);
        mViewPage.setCurrentItem(0);
        mGank.setSelected(true);
    }
}
