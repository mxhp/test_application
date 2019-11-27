package com.xhp.testutils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class XRecycleView extends RecyclerView {
    public XRecycleView(@NonNull Context context) {
        super(context);
    }

    public XRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSelection(int position){
        RecyclerView.ViewHolder holder = findViewHolderForAdapterPosition(position);
        holder.itemView.requestFocus();
    }
}
