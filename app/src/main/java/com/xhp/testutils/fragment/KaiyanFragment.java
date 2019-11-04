package com.xhp.testutils.fragment;

import android.view.View;

import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.presenter.BasePresenter;

public class KaiyanFragment extends BaseFragment {
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_kaiyan;
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
