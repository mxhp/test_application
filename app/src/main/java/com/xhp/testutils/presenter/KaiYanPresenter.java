package com.xhp.testutils.presenter;

import com.xhp.testutils.bean.OpenEyesIndexInfo;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.contract.KaiYanContract;
import com.xhp.testutils.model.BaseEngin;
import com.xhp.testutils.model.KaiYanModel;

import java.util.ArrayList;

public class KaiYanPresenter extends BasePresenter<KaiYanContract.View, KaiYanModel> implements KaiYanContract.Presenter<KaiYanContract.View> {
    private int page = 1;

    @Override
    protected KaiYanModel createEngin() {
        return new KaiYanModel();
    }

    @Override
    public void getHomeData(boolean isFresh) {
        if (isFresh) {
            page = 1;
        } else {
            page++;
        }
        if (mNetEnginRef != null && mNetEnginRef.get() != null) {
            getNetEngin().get().getHomeData(page, new BaseEngin.ResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    if (mViewRef != null && mViewRef.get() != null) {
                        if (o != null) {
                            OpenEyesIndexInfo openEyesIndexInfo = (OpenEyesIndexInfo) o;
                            if (isFresh) {
                                if (openEyesIndexInfo.getItemList() == null) {
                                    mViewRef.get().loadHomeDataHome(new ArrayList<OpenEyesIndexItemBean>());
                                } else {
                                    mViewRef.get().loadHomeDataHome(openEyesIndexInfo.getItemList());
                                }
                            } else {
                                if (openEyesIndexInfo.getItemList() == null) {
                                    mViewRef.get().loadMoreHomeData(new ArrayList<OpenEyesIndexItemBean>());
                                } else {
                                    mViewRef.get().loadMoreHomeData(openEyesIndexInfo.getItemList());
                                }
                            }
                        }
                    }

                }

                @Override
                public void onFailed(int code, String message) {
                    if (mViewRef != null && mViewRef.get() != null) {
                        mViewRef.get().showError(code, message);
                    }
                }
            });
        }
    }
}
