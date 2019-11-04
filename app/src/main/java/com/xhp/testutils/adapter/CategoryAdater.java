package com.xhp.testutils.adapter;

import android.content.Context;
import android.view.View;

import com.xhp.testutils.R;
import com.xhp.testutils.bean.Category;
import com.xhp.testutils.util.DateUtil;


public class CategoryAdater extends CommonAdapter4RecyclerView<Category.ResultsBean> implements ListenerWithPosition.OnClickWithPositionListener<CommonHolder4RecyclerView> {


    public CategoryAdater(Context mContext) {
        super(R.layout.item, mContext);
    }

    @Override
    public void convert(CommonHolder4RecyclerView holder, Category.ResultsBean resultsBean) {
        holder.setTextViewText(R.id.tv_item_title, resultsBean.desc == null ? "unknown" : resultsBean.desc);
        holder.setTextViewText(R.id.tv_item_publisher, resultsBean.who == null ? "unknown" : resultsBean.who);
        holder.setTextViewText(R.id.tv_item_time, DateUtil.dateFormat(resultsBean.publishedAt));
        holder.setOnClickListener(this, R.id.ll_item);
    }

    @Override
    public void onClick(View v, int position, CommonHolder4RecyclerView holder) {
        if (mDatas == null || mDatas.get(position) == null) {
//            Toasty.error(mContext, "数据异常").show();
            return;
        }
//        Toasty.normal(mContext, "to add").show();
    }
}
