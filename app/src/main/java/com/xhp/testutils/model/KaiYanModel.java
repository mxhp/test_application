package com.xhp.testutils.model;

import com.xhp.testutils.R;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.bean.OpenEyesIndexInfo;
import com.xhp.testutils.network.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KaiYanModel extends BaseEngin {
    public void getHomeData(int page, BaseEngin.ResultCallBack resultCallBack) {
        DataManager.getKaiYanApi().getHomeData(page).enqueue(new Callback<OpenEyesIndexInfo>() {
            @Override
            public void onResponse(Call<OpenEyesIndexInfo> call, Response<OpenEyesIndexInfo> response) {
                if (resultCallBack != null) {
                    resultCallBack.onSuccess(response);
                }
            }

            @Override
            public void onFailure(Call<OpenEyesIndexInfo> call, Throwable t) {
                if (resultCallBack != null) {
                    resultCallBack.onFailed(BaseEngin.Exception_HTTP, t.getMessage());
                }
            }
        });
    }
}
