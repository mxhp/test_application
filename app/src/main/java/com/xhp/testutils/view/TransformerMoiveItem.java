package com.xhp.testutils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xhp.testutils.R;

public class TransformerMoiveItem extends FrameLayout {

    private ImageView mRoundImageView;

    public TransformerMoiveItem(@NonNull Context context) {
        this(context, null);
    }

    public TransformerMoiveItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_pager_item, this);
    }

    public void onDestroy() {
        if (null != mRoundImageView) {
            mRoundImageView.setImageResource(0);
            mRoundImageView = null;
        }
    }
}
