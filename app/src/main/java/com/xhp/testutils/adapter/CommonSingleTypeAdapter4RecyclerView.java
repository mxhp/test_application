package com.xhp.testutils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonSingleTypeAdapter4RecyclerView<T> extends RecyclerView.Adapter {
    private View mView;
    protected List<T> mDatas;
    private int layoutId;
    public Context mContext;

    public CommonSingleTypeAdapter4RecyclerView(int layoutId, Context mContext) {
        this.layoutId = layoutId;
        this.mContext = mContext;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = layoutInflater.inflate(layoutId, viewGroup, false);
        return new CommonHolder4RecyclerView(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CommonHolder4RecyclerView commonHolder = (CommonHolder4RecyclerView) viewHolder;
        commonHolder.position = i;
        convert(commonHolder, mDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void convert(CommonHolder4RecyclerView commonHolder4RecyclerView, T t);
}
