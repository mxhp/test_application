package com.xhp.testutils.fragment;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.CategoryAdater;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.contract.CategoryContract;
import com.xhp.testutils.presenter.CategoryPresenter;
import com.xhp.testutils.util.DataDiff;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import es.dmoral.toasty.Toasty;


public class CategoryFragment extends BaseFragment<CategoryPresenter> implements CategoryContract.View {

    public static final String CATEGORY_TITLE = "categoryName";
    private String mCategory = "";
    private XRecyclerView mRecycleview;
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
        mRecycleview = (XRecyclerView) view.findViewById(R.id.xrecyclerview);
        mCategoryAdapter = new CategoryAdater(getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(layoutManager);
        mRecycleview.setAdapter(mCategoryAdapter);
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecycleview.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);
        mRecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                mPresenter.getCategoryData(false);
            }
        });
    }

    @Override
    protected CategoryPresenter createPresenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getCategoryData(true);
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadCategoryData(Category category) {
        mCategoryAdapter.setDatas(category.results);
        mCategoryAdapter.notifyDataSetChanged();
    }

    @Override
    public String getCategoryName() {
        return mCategory;
    }

    @Override
    public void loadMoreCategoryData(Category category) {
        List<Category.ResultsBean> resultsBeans = mCategoryAdapter.getDatas();
        List<Category.ResultsBean> data = new ArrayList<>();
        for (Category.ResultsBean a : resultsBeans) {
            try {
                data.add((Category.ResultsBean) a.clone());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data.addAll(category.results);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DataDiff(resultsBeans, data), true);
        result.dispatchUpdatesTo(mCategoryAdapter);
        mCategoryAdapter.setDatas(data);
        mRecycleview.loadMoreComplete();
//        Toasty.normal(Objects.requireNonNull(getContext()), "加载成功").show();
    }

    @Override
    public void showError(int code, String errorMsg) {
        super.showError(code, errorMsg);
        mRecycleview.reset();
        Toasty.error(Objects.requireNonNull(getContext()), "加载数据失败,请稍后再试!").show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRecycleview != null) {
            mRecycleview.destroy();
            mRecycleview = null;
        }
    }
}
