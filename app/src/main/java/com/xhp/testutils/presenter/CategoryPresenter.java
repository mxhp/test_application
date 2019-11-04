package com.xhp.testutils.presenter;

import com.xhp.testutils.bean.Category;
import com.xhp.testutils.contract.CategoryContract;
import com.xhp.testutils.model.BaseEngin;
import com.xhp.testutils.model.CategoryModel;

public class CategoryPresenter extends BasePresenter<CategoryContract.View, CategoryModel> implements CategoryContract.Presenter<CategoryContract.View> {
    @Override
    public void getCategoryData(String categoryName, int number, int page) {
        if (mViewRef != null && mViewRef.get() != null) {
            getNetEngin().get().getCateData(categoryName, number, page, new BaseEngin.ResultCallBack() {
                @Override
                public void onSuccess(Object o) {
                    mViewRef.get().loadCategoryData((Category) o);
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
