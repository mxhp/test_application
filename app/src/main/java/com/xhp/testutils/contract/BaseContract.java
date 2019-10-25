package com.xhp.testutils.contract;

public interface BaseContract {

    interface BaseView {
        /**
         * 开始加载中
         */
        void showLoading();

        /**
         * 错误、为空回调
         *
         * @param code     0：为空 -1：失败
         * @param errorMsg 描述信息
         */
        void showError(int code, String errorMsg);

        /**
         *  隐藏加载
         */
        void hideLoading();
    }

    interface BasePresenter<V> {

        void attachView(V view);

        void detachView();
    }
}
