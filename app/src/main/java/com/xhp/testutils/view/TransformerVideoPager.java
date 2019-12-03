package com.xhp.testutils.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xhp.testutils.R;
import com.xhp.testutils.util.DisplayUtils;

import java.util.List;

public class TransformerVideoPager<T> extends RelativeLayout {
    private TextView mVideoIndexNum, mViewTitle;
    private ViewPager mViewPager;
    private MusicJukeBoxBackgroundLayout mMusicJukeBoxBackgroundLayout;
    private TransformerViewpager mAdapter;
    private List<T> mDataBeans;

    public TransformerVideoPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_pager_transforme, this);
        int emptyHeight = DisplayUtils.getStatusBarHeight(getContext()) + DisplayUtils.dp2px(20f, getContext());
        findViewById(R.id.view_empty).getLayoutParams().height = emptyHeight;
        mViewPager = (ViewPager) findViewById(R.id.view_item_pager);
        //ViewPager的父容器高度确定
        RelativeLayout pagerLayout = (RelativeLayout) findViewById(R.id.re_item_pager_view);
        int screenWidth = DisplayUtils.getScreenWidth(context);
        int width = screenWidth * 8 / 10;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) pagerLayout.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
        layoutParams.height = width * 9 / 16;
        pagerLayout.setLayoutParams(layoutParams);
        //ViewPager宽度为父容器8/10，高度与父容器一致
        LayoutParams params = new LayoutParams(width, width * 9 / 16);
        mViewPager.setLayoutParams(params);
    }


    private class TransformerViewpager extends PagerAdapter {

        @Override
        public int getCount() {
            return mDataBeans == null ? 0 : mDataBeans.size() + 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return false;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
