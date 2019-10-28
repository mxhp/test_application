package com.xhp.testutils.model;


import com.xhp.testutils.bean.Category;
import com.xhp.testutils.network.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartModel extends BaseEngin {
    public void getRandomPicture(BaseEngin.ResultCallBack callBack){
        DataManager.getGankApi().getRandomBeauties(1).enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (callBack != null) {
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                if (callBack != null) {
                    callBack.onFailed(BaseEngin.Exception_HTTP,t.getMessage());
                }
            }
        });
    }
}
