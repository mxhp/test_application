package com.xhp.testutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.C;
import com.xhp.testutils.bean.FindBean;
import com.xhp.testutils.bean.HotBean;
import com.xhp.testutils.network.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    private Button mFind;
    private Button mSearch;
    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
    }

    public void find(View view) {
        DataManager.getKaiYanApi().getFindData().enqueue(new Callback<List<FindBean>>() {
            @Override
            public void onResponse(Call<List<FindBean>> call, Response<List<FindBean>> response) {
                mResult.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<List<FindBean>> call, Throwable t) {

            }
        });
    }

    public void search(View view) {
        DataManager.getKaiYanApi().getSearchData(10,"体育",0).enqueue(new Callback<HotBean>() {
            @Override
            public void onResponse(Call<HotBean> call, Response<HotBean> response) {
                mResult.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<HotBean> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        mFind = findViewById(R.id.find);
        mSearch = findViewById(R.id.search);
        mResult = findViewById(R.id.result);
    }
}
