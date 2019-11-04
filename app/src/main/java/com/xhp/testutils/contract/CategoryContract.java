package com.xhp.testutils.contract;

import com.xhp.testutils.bean.Category;


public interface CategoryContract {

    interface View extends BaseContract.BaseView {
        void loadCategoryData(Category category);
    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {
       void getCategoryData(String categoryName,int number,int page);
    }
}
