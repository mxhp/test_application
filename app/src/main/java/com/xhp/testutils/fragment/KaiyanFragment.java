package com.xhp.testutils.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.KaiYanHomeAdapter;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.contract.KaiYanContract;
import com.xhp.testutils.presenter.KaiYanPresenter;

import java.util.List;

public class KaiyanFragment extends BaseFragment<KaiYanPresenter> implements KaiYanContract.View, KaiYanHomeAdapter.OnItemClickListener {

    private View mViewAnchor;
    private XRecyclerView mRecycleview;
    private KaiYanHomeAdapter mKaiYanHomeAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_kaiyan;
    }

    @Override
    protected void initViews(View view) {
        mViewAnchor = view.findViewById(R.id.view_anchor);
        mRecycleview = view.findViewById(R.id.xrecyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleview.setLayoutManager(layoutManager);
        mKaiYanHomeAdapter = new KaiYanHomeAdapter(getContext(), this);
        mRecycleview.setAdapter(mKaiYanHomeAdapter);
        mRecycleview.setPullRefreshEnabled(false);
        mRecycleview.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecycleview.setLoadingMoreProgressStyle(ProgressStyle.SemiCircleSpin);
        mRecycleview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                mPresenter.getHomeData(false);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getHomeData(true);
    }

    @Override
    protected KaiYanPresenter createPresenter() {
        return new KaiYanPresenter();
    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void loadHomeDataHome(List<OpenEyesIndexItemBean> data) {
        mKaiYanHomeAdapter.setData(data);
        mKaiYanHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreHomeData(List<OpenEyesIndexItemBean> newData) {
        mKaiYanHomeAdapter.addData(newData);
        mKaiYanHomeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int posotion, long musicID) {

    }
}
