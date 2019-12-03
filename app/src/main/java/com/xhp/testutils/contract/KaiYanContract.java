package com.xhp.testutils.contract;

import com.xhp.testutils.bean.Category;
import com.xhp.testutils.bean.HomeDataBean;

public interface KaiYanContract {

    interface View extends BaseContract.BaseView {

        void loadHomeDataHome(HomeDataBean homeDataBean);

        void loadMoreHomeData(HomeDataBean homeDataBean);

    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {

        void getHomeData(boolean isFresh);
    }
}
