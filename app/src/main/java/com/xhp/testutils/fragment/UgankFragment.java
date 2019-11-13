package com.xhp.testutils.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.HomeViewPager;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.presenter.BasePresenter;
import com.xhp.testutils.util.GankConstant;

import java.util.ArrayList;
import java.util.List;

public class UgankFragment extends BaseFragment {


    private ImageView mBackdrop;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsing;
    private DachshundTabLayout mTabs;
    private AppBarLayout mActionBar;
    private ViewPager mViewpager;
    private CategoryFragment appFragment, androidFragment, iOSFragment, frontFragment, resFragment, referenceFragment;

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
    protected void initData() {
        super.initData();
        String[] titles = {
                GankConstant.CATEGORY_NAME_APP,
                GankConstant.CATEGORY_NAME_ANDROID,
                GankConstant.CATEGORY_NAME_IOS,
                GankConstant.CATEGORY_NAME_FRONT_END,
                GankConstant.CATEGORY_NAME_RECOMMEND,
                GankConstant.CATEGORY_NAME_RESOURCE};
        HomeViewPager infoPagerAdapter = new HomeViewPager(titles, getChildFragmentManager());

        // App
        appFragment = CategoryFragment.newInstance(titles[0]);
        // Android
        androidFragment = CategoryFragment.newInstance(titles[1]);
        // iOS
        iOSFragment = CategoryFragment.newInstance(titles[2]);
        // 前端
        frontFragment = CategoryFragment.newInstance(titles[3]);
        // 瞎推荐
        referenceFragment = CategoryFragment.newInstance(titles[4]);
        // 拓展资源s
        resFragment = CategoryFragment.newInstance(titles[5]);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(appFragment);
        fragments.add(androidFragment);
        fragments.add(iOSFragment);
        fragments.add(frontFragment);
        fragments.add(referenceFragment);
        fragments.add(resFragment);
        infoPagerAdapter.setFragmentList(fragments);
        mViewpager.setAdapter(infoPagerAdapter);
        mTabs.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(1);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void hideLoading() {

    }
}
