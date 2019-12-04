package com.xhp.testutils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonMutiType4RecyclerView<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public CommonMutiType4RecyclerView(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<T> datas) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }
        this.mData.clear();
        this.mData = datas;
    }

    public void addData(List<T> datas) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }
        this.mData.addAll(datas);
    }

    @NonNull
    @Override
    public V onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return CreateViewHolder(viewGroup, position);
    }

    protected abstract V CreateViewHolder(ViewGroup viewGroup, int position);


    @Override
    public int getItemCount() {
        return this.mData == null ? 0 : this.mData.size();
    }

    public List<T> getData(){
        if (mData==null){
            mData=new ArrayList<>();
        }
        return mData;
    }
}
