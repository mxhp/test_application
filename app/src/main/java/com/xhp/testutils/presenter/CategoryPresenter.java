package com.xhp.testutils.presenter;

import com.xhp.testutils.bean.Category;
import com.xhp.testutils.contract.CategoryContract;
import com.xhp.testutils.model.BaseEngin;
import com.xhp.testutils.model.CategoryModel;

public class CategoryPresenter extends BasePresenter<CategoryContract.View, CategoryModel> implements CategoryContract.Presenter<CategoryContract.View> {
    private int page = 1;

    @Override
    public void getCategoryData(boolean isFresh) {
        if (isFresh){
            page =1 ;
        }else {
            page++;
        }
        if (mViewRef != null && mViewRef.get() != null) {
            getNetEngin().get().getCateData(mViewRef.get().getCategoryName(), 10, page, new BaseEngin.ResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    if(isFresh) {
                        mViewRef.get().loadCategoryData((Category) o);
                    }else {
                        mViewRef.get().loadMoreCategoryData((Category) o);
                    }
                }

                @Override
                public void onFailed(int code, String message) {
                    mViewRef.get().showError(code, message);
                }
            });
        }
    }

    @Override
    protected CategoryModel createEngin() {
        return new CategoryModel();
    }


}
