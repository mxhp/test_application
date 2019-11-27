package com.xhp.testutils.contract;

import com.xhp.testutils.bean.Category;

public interface KaiYanContract {

    interface View extends BaseContract.BaseView {
        void loadCategoryData(Category category);

        String getCategoryName();

        void loadMoreCategoryData(Category category) ;

    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {

        void getHomeData(boolean isFresh);
    }
}
