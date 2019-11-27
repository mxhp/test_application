package com.xhp.testutils.presenter;

import com.xhp.testutils.contract.KaiYanContract;
import com.xhp.testutils.model.KaiYanModel;

public class KaiYanPresenter extends BasePresenter<KaiYanContract.View, KaiYanModel> implements KaiYanContract.Presenter<KaiYanContract.View> {
    private int page = 1;

    @Override
    protected KaiYanModel createEngin() {
        return new KaiYanModel();
    }

    @Override
    public void getHomeData(boolean isFresh) {
         if (isFresh){
             page=1;
         }else {
             page++;
         }
         if (mNetEnginRef!=null&&mNetEnginRef.get()!=null){
//             getNetEngin().get().getCateData();
         }
    }
}
