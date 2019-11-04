package com.xhp.testutils.model;


import com.xhp.testutils.bean.Category;
import com.xhp.testutils.network.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryModel extends BaseEngin {

    public void getCateData(String category, int number, int page, BaseEngin.ResultCallBack resultCallBack) {
        DataManager.getGankApi().getCategoryDate(category, number, page).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                if (resultCallBack != null) {
                    resultCallBack.onFailed(BaseEngin.Exception_HTTP, t.getMessage());
                }
            }
        });
    }

}
