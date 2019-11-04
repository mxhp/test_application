package com.xhp.testutils.fragment;

import android.os.Bundle;
import android.view.View;

import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.presenter.BasePresenter;

public class CategoryFragment extends BaseFragment {

    public static final String CATEGORY_TITLE = "categoryName";
    private String mCategory = "";

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

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void hideLoading() {

    }
}
