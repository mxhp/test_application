package com.xhp.testutils.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhp.testutils.contract.BaseContract;
import com.xhp.testutils.presenter.BasePresenter;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseContract.BaseView {

    protected P mPresenter;
    protected abstract int getLayoutID();
    protected abstract void initViews(View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        initBundle();
        return inflater.inflate(getLayoutID(),null,false);
    }

    protected void initBundle() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter=createPresenter();
        if(null!=mPresenter){
            mPresenter.attachView(this);
        }
        initViews(view);
    }

    /**
     * 交由子类实现自己指定的Presenter,可以为空
     * @return 子类持有的继承自BasePresenter的Presenter
     */
    protected abstract P createPresenter();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null!=mPresenter){
            mPresenter.detachView();
            mPresenter=null;
        }
    }

    protected void onInvisible() {}

    protected void onVisible() {}

    @Override
    public void showLoading() {}

    @Override
    public void showError(int code, String errorMsg) {}

}
