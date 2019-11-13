package com.xhp.testutils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xhp.testutils.adapter.CommonAdapter4RecyclerView;
import com.xhp.testutils.adapter.CommonHolder4RecyclerView;
import com.xhp.testutils.util.AnimatorUtils;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class TestAdater extends CommonAdapter4RecyclerView<String> {
    private OnclikCallBack onclikCallBack;

    public TestAdater(Context mContext) {
        super(R.layout.item_circle, mContext);
    }

    public void setOnclikCallBack(OnclikCallBack onclikCallBack) {
        this.onclikCallBack = onclikCallBack;
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, String s) {
//        Glide.with(mContext).load("http://guolin.tech/book.png").into((ImageView) holder.getView(R.id.circle));
        holder.mConvertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ((CircleImageView) holder.getView(R.id.circle)).setBorderColor(Color.WHITE);
                    ((CircleImageView) holder.getView(R.id.circle)).setImageResource(R.mipmap.search_heard);
                    AnimatorUtils.scaleRelCenter(mContext, holder.mConvertView, R.animator.item_scale_normal2big);
                } else {
                    ((CircleImageView) holder.getView(R.id.circle)).setBorderColor(Color.TRANSPARENT);
                    ((CircleImageView) holder.getView(R.id.circle)).setImageResource(R.mipmap.ic_launcher_round);
                    AnimatorUtils.scaleRelCenter(mContext, holder.mConvertView, R.animator.item_scale_big2normal);
                }
            }
        });
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
