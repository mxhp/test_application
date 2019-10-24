package com.xhp.testutils.contract;

import com.xhp.testutils.presenter.BasePresenter;
import com.xhp.testutils.view.BaseView;

public interface StartContract {

    interface StartView extends BaseView {
    }


    interface Presenter extends BasePresenter {
        void getImageUrl();
    }

}
