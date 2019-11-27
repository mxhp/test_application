package com.xhp.testutils.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.kekstudio.dachshundtablayout.DachshundTabLayout;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.HomeViewPager;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.network.DataManager;
import com.xhp.testutils.presenter.BasePresenter;
import com.xhp.testutils.util.GankConstant;
import com.xhp.testutils.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UgankFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mBeauty;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsing;
    private DachshundTabLayout mTabs;
    private AppBarLayout mActionBar;
    private ViewPager mViewpager;
    private CategoryFragment appFragment, androidFragment, iOSFragment, frontFragment, resFragment, referenceFragment;
    private LinearLayout mSearchView;
    private AppCompatImageView mCollectView, mSettingView;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_ugank;
    }

    @Override
    protected void initViews(View view) {

        mBeauty = view.findViewById(R.id.backdrop);
        mToolbar = view.findViewById(R.id.toolbar);
        mCollapsing = view.findViewById(R.id.collapsing);
        mTabs = view.findViewById(R.id.tabs);
        mActionBar = view.findViewById(R.id.action_bar);
        mViewpager = view.findViewById(R.id.viewpager);
        mSearchView = view.findViewById(R.id.ll_home_search);
        mCollectView = view.findViewById(R.id.iv_home_collection);
        mSettingView = view.findViewById(R.id.iv_home_setting);
        mSettingView.setOnClickListener(this);
        mBeauty.setOnClickListener(this);
        mSearchView.setOnClickListener(this);
        mCollectView.setOnClickListener(this);
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
        String url = SharedPreferencesManager.getInstance(getContext())
                .getValue(SharedPreferencesManager.SAVE_URL);
        if (!TextUtils.isEmpty(url)) {
            Glide.with(getContext()).load(url).into(mBeauty);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backdrop:
                DataManager.getGankApi().getRandomBeauties(1).enqueue(new Callback<Category>() {
                    @Override
                    public void onResponse(Call<Category> call, Response<Category> response) {
                        String url = ((Category) (response.body())).results.get(0).url;
                        Glide.with(getContext()).load(url).into(mBeauty);
                        SharedPreferencesManager.getInstance(getContext())
                                .putString(SharedPreferencesManager.SAVE_URL, url);
                    }

                    @Override
                    public void onFailure(Call<Category> call, Throwable t) {

                    }
                });
                break;
            case R.id.ll_home_search:
                break;
            case R.id.iv_home_collection:
                break;
            case R.id.iv_home_setting:
                break;

        }
    }
}
