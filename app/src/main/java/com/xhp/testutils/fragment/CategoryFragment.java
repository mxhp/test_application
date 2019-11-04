package com.xhp.testutils.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xhp.testutils.R;
import com.xhp.testutils.adapter.CategoryAdater;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.contract.CategoryContract;
import com.xhp.testutils.presenter.CategoryPresenter;


public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract.View {

    public static final String CATEGORY_TITLE = "categoryName";
    private String mCategory = "";
    private View view;
    private RecyclerView mRecycleview;
    private CategoryAdater mCategoryAdapter;

    public static CategoryFragment newInstance(String title) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_TITLE, title);
        categoryFragment.setArguments(bundle);
        return categoryFragment;
    }

    @Override
    protected void initBundle() {
        super.initBundle();
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCategory = bundle.getString(CATEGORY_TITLE);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initViews(View view) {
        mRecycleview = (RecyclerView) view.findViewById(R.id.xrecyclerview);
        mCategoryAdapter = new CategoryAdater(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(layoutManager);
//        mRecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mRecycleview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
//        mRecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
////                Toasty.info(getContext(), "刷新").show();
//            }
//
//            @Override
//            public void onLoadMore() {
////                Toasty.info(getContext(), "加载更多").show();
//            }
//        });
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getCategoryData(mCategory, 1, 1);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadCategoryData(Category category) {
        mCategoryAdapter.setDatas(category.results);
        mCategoryAdapter.notifyDataSetChanged();
    }

}
