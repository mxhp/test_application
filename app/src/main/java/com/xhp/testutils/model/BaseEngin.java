package com.xhp.testutils.model;

public class BaseEngin {

    public static final int Exception_HTTP = -1;
    public static final int Exception_EMPTY = 0;

    public interface ResultCallBack<T> {

        void onSuccess(T t);

        void onFailed(int code,String message);
    }

}
