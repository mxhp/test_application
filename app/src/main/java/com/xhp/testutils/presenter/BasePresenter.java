package com.xhp.testutils.presenter;

import com.xhp.testutils.view.BaseView;

public interface BasePresenter {

     void attachView(BaseView view);

     void detachView();

}
