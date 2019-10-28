package com.xhp.testutils.contract;

public interface StartContract {

    interface View extends BaseContract.BaseView {
        /**
         *  展示启动图片
         * @param string
         */
         void  showStartImage(String string);

        /**
         *  跳转到首页
         */
        void gotoMain();
    }

    interface Presenter<V> extends BaseContract.BasePresenter<V> {
        /**
         *  获取地址的urls
         */
        void getImages();

    }
}
