package com.xhp.testutils.dialog;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xhp.testutils.util.DisplayUtils;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private float left;
    private float top;
    private float right;
    private float bottom;

    public SpaceItemDecoration(Context context, int left, int top, int right, int bottom) {
        this.context = context;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.right = DisplayUtils.dp2px(right, context);
        outRect.bottom = DisplayUtils.dp2px(bottom, context);
        outRect.top = DisplayUtils.dp2px(top, context);
        outRect.left = DisplayUtils.dp2px(left, context);
    }
}
