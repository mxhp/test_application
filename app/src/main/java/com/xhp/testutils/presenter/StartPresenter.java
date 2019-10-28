package com.xhp.testutils.presenter;

import com.xhp.testutils.bean.Category;
import com.xhp.testutils.contract.StartContract;
import com.xhp.testutils.model.BaseEngin;
import com.xhp.testutils.model.StartModel;

public class StartPresenter extends BasePresenter<StartContract.View, StartModel> implements StartContract.Presenter<StartContract.View>{

    @Override
    public void getImages() {
        if (mViewRef != null&&mViewRef.get()!=null) {
            mViewRef.get().showLoading();
            getNetEngin().get().getRandomPicture(new BaseEngin.ResultCallBack() {
                @Override
                public void onSuccess(Object object) {
                    mViewRef.get().showStartImage(((Category)object).results.get(0).url);
                }

                @Override
                public void onFailed(int code,String message) {
                    mViewRef.get().showError(code,message);
                }
            });
        }
    }



    @Override
    protected StartModel createEngin() {
        return new StartModel();
    }
}
