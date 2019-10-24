package com.xhp.testutils.presenter;

import com.xhp.testutils.contract.StartContract;
import com.xhp.testutils.view.BaseView;

public class StartPresenter implements StartContract.Presenter {

    BaseView baseView;

    @Override
    public void attachView(BaseView view) {
        baseView = view;
    }

    @Override
    public void detachView() {
        baseView = null;
    }

    @Override
    public void getImageUrl() {

    }
}
