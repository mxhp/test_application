package com.xhp.testutils.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.xhp.testutils.R;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.business.WebActvity;
import com.xhp.testutils.util.DateUtil;



public class CategoryAdater extends CommonAdapter4RecyclerView<Category.ResultsBean> {


    public CategoryAdater(Context mContext) {
        super(R.layout.item, mContext);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, Category.ResultsBean resultsBean) {
        holder.setTextViewText(R.id.tv_item_title, resultsBean.desc == null ? "unknown" : resultsBean.desc);
        holder.setTextViewText(R.id.tv_item_publisher, resultsBean.who == null ? "unknown" : resultsBean.who);
        holder.setTextViewText(R.id.tv_item_time, DateUtil.dateFormat(resultsBean.publishedAt));
        if (resultsBean.images != null && resultsBean.images.size() > 0) {
            Glide.with(mContext).load(resultsBean.images.get(0) + "?imageView2/0/w/280").into((ImageView) holder.getView(R.id.iv_item_img));
        } else {
            holder.getView(R.id.iv_item_img).setVisibility(View.GONE);
        }
        holder.mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActvity.class);
                intent.putExtra(WebActvity.URL_KEY, resultsBean.url);
                mContext.startActivity(intent);
            }
        });
    }

}
