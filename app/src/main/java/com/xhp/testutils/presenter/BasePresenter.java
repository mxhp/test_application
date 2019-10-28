package com.xhp.testutils.presenter;

import com.xhp.testutils.contract.BaseContract;
import com.xhp.testutils.model.BaseEngin;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseContract.BaseView, M extends BaseEngin>
        implements BaseContract.BasePresenter<V> {

    //MVP中的V
    protected WeakReference<V> mViewRef;
    //MVP中的M
    protected WeakReference<M> mNetEnginRef;

    /**
     * 初始化构造
     */
    public BasePresenter() {
        this.mNetEnginRef = new WeakReference<M>(createEngin());
    }

    /**
     * 返回 Date Model
     * @return DataModel
     */
    public synchronized WeakReference<M> getNetEngin(){
        if(null==mNetEnginRef||null==mNetEnginRef.get()){
            mNetEnginRef=new WeakReference<M>(createEngin());
        }
        return mNetEnginRef;
    }


    /**
     * 创建model
     *
     * @return
     */
    protected abstract M createEngin();

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (null != mViewRef && null != mViewRef.get()) {
            mViewRef.clear();
        }
        mViewRef = null;
        if (null != mNetEnginRef && null != mNetEnginRef.get()) {
            mNetEnginRef.clear();
        }
        mNetEnginRef.clear();
    }


}
