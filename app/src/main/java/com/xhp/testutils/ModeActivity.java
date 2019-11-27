package com.xhp.testutils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xhp.testutils.dialog.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ModeActivity extends AppCompatActivity implements TestAdater.OnclikCallBack {
    private RecyclerView mModeList;
    private Context mContext;
    private TestAdater testAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        mContext = this;
        initViews();
    }

    private void initViews() {
        mModeList = findViewById(R.id.mode_list);
        if (mModeList != null) {
            mModeList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        }
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(mContext, 30, 0, 0, 0);
        mModeList.addItemDecoration(spaceItemDecoration);
        new LinearSnapHelper().attachToRecyclerView(mModeList);
        testAdater = new TestAdater(mContext);
        testAdater.setOnclikCallBack(this);
        String[] strings = {};
        testAdater.setDatas(Arrays.asList(strings));
        mModeList.setAdapter(testAdater);
        List<String> listNewData = new ArrayList<>();
        listNewData.add("aaa");
        listNewData.add("bbb");
        listNewData.add("ccc");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testAdater.setDatas(listNewData);
                testAdater.setIndex(2);
                testAdater.notifyDataSetChanged();
//                RecyclerView.ViewHolder holder = mModeList.findViewHolderForAdapterPosition(1);
//                testAdater.setFocusIndex(holder);

//                testAdater.setIndex(-1);
//                RecyclerView.ViewHolder holder = mModeList.findViewHolderForAdapterPosition(1);
//                if (holder!=null) {
//                    holder.itemView.requestFocusFromTouch();
//                }else {
//                    Log.e("jjjjj","holder null");
//                }
            }
        }, 3 * 1000);
    }

    @Override
    public void onclick(int postion, String messageBean) {
        Toasty.normal(mContext, "posiion=" + postion + " message=" + messageBean).show();
    }
}
