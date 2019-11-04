package com.xhp.testutils.contract;

public interface CategoryContract {

    interface View extends BaseContract.BaseView {
    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {
    }
}
