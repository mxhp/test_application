package com.xhp.testutils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xhp.testutils.adapter.CommonSingleTypeAdapter4RecyclerView;
import com.xhp.testutils.adapter.CommonHolder4RecyclerView;
import com.xhp.testutils.util.AnimatorUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestAdater extends CommonSingleTypeAdapter4RecyclerView<String> {
    private OnclikCallBack onclikCallBack;
    private int index =-1;

    public TestAdater(Context mContext) {
        super(R.layout.item_circle, mContext);
    }

    public void setOnclikCallBack(OnclikCallBack onclikCallBack) {
        this.onclikCallBack = onclikCallBack;
    }

    public void setIndex(int i){
        this.index =i ;
    }

    public void setFocusIndex(RecyclerView.ViewHolder holder){
        holder.itemView.requestFocus();
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, String s) {
//        Glide.with(mContext).load("http://guolin.tech/book.png").into((ImageView) holder.getView(R.id.circle));
        holder.mConvertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("jjjjjj","true");
                    ((CircleImageView) holder.getView(R.id.circle)).setBorderColor(Color.WHITE);
                    ((CircleImageView) holder.getView(R.id.circle)).setImageResource(R.mipmap.search_heard);
                    AnimatorUtils.scaleRelCenter(mContext, holder.mConvertView, R.animator.item_scale_normal2big);
                } else {
                    Log.i("xhp","false");
                    ((CircleImageView) holder.getView(R.id.circle)).setBorderColor(Color.TRANSPARENT);
                    ((CircleImageView) holder.getView(R.id.circle)).setImageResource(R.mipmap.app_logo);
                    AnimatorUtils.scaleRelCenter(mContext, holder.mConvertView, R.animator.item_scale_big2normal);
                }
            }
        });
        if (holder.position==index){
            holder.mConvertView.requestFocus();
        }
        holder.mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toasty.info(mContext, String.valueOf(holder.position)).show();
                if (onclikCallBack != null) {
                    onclikCallBack.onclick(holder.position, s);
                }
            }
        });
    }

    public interface OnclikCallBack {
        void onclick(int postion, String messageBean);
    }
}
