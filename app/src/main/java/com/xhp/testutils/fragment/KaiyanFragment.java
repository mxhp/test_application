package com.xhp.testutils.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.dueeeke.videoplayer.player.VideoView;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.KaiYanHomeAdapter;
import com.xhp.testutils.base.BaseFragment;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.contract.KaiYanContract;
import com.xhp.testutils.presenter.KaiYanPresenter;

import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

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
        mRecycleview.loadMoreComplete();
    }

    @Override
    public void onItemClick(View view, int posotion, long musicID) {
        OpenEyesIndexItemBean openEyesIndexItemBean = mKaiYanHomeAdapter.getData().get(posotion);
        Log.d("onItemClick", "position =" + posotion);
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

    @Override
    protected void onInvisible() {
        super.onInvisible();
//        if (VideoViewManager.instance().();)
        VideoViewManager.instance().pause();
    }
}
