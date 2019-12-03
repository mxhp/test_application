package com.xhp.testutils.fragment;

import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.HomeDataBean;
import com.xhp.testutils.contract.KaiYanContract;
import com.xhp.testutils.presenter.BasePresenter;

public class KaiyanFragment extends BaseFragment implements KaiYanContract.View {

    private View mViewAnchor;
    private XRecyclerView mXrecyclerview;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_kaiyan;
    }

    @Override
    protected void initViews(View view) {

        mViewAnchor = view.findViewById(R.id.view_anchor);
        mXrecyclerview = view.findViewById(R.id.xrecyclerview);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void loadHomeDataHome(HomeDataBean homeDataBean) {

    }

    @Override
    public void loadMoreHomeData(HomeDataBean homeDataBean) {

    }
}
