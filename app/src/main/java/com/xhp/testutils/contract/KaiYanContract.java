package com.xhp.testutils.contract;

import com.xhp.testutils.bean.OpenEyesIndexItemBean;

import java.util.List;

public interface KaiYanContract {

    interface View extends BaseContract.BaseView {

        void loadHomeDataHome(List<OpenEyesIndexItemBean> data);

        void loadMoreHomeData(List<OpenEyesIndexItemBean> newData);

    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {

        void getHomeData(boolean isFresh);
    }
}
