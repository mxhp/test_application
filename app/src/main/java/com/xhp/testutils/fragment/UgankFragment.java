package com.xhp.testutils.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.presenter.BasePresenter;

public class UgankFragment extends BaseFragment {


    private ImageView mBackdrop;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsing;
    private TabLayout mTabs;
    private AppBarLayout mActionBar;
    private ViewPager mViewpager;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_ugank;
    }

    @Override
    protected void initViews(View view) {

        mBackdrop = view.findViewById(R.id.backdrop);
        mToolbar = view.findViewById(R.id.toolbar);
        mCollapsing = view.findViewById(R.id.collapsing);
        mTabs = view.findViewById(R.id.tabs);
        mActionBar = view.findViewById(R.id.action_bar);
        mViewpager = view.findViewById(R.id.viewpager);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void hideLoading() {

    }
}
