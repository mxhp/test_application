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
import com.xhp.testutils.amator.TransformerPageAnimation;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class TransformerVideoPager extends RelativeLayout {
    private TextView mVideoIndexNum, mViewTitle;
    private ViewPager mViewPager;
    private MusicJukeBoxBackgroundLayout mBackgroundLayout;
    private TransformerViewpager mAdapter;
    private List<OpenEyesIndexItemBean> mDataBeans;

    public TransformerVideoPager(Context context) {
        this(context, null);
    }

    public TransformerVideoPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_pager_transforme, this);
        int emptyHeight = DisplayUtils.getStatusBarHeight(getContext()) + DisplayUtils.dp2px(20f, getContext());
        findViewById(R.id.view_empty).getLayoutParams().height = emptyHeight;
        mViewPager = (ViewPager) findViewById(R.id.view_item_pager);
        mAdapter = new TransformerViewpager();
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                //注意这里也需要获取真实的position
                position %= mDataBeans.size();
                setPagerData(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
        mViewPager.setPageTransformer(true, new TransformerPageAnimation());
        mBackgroundLayout = (MusicJukeBoxBackgroundLayout) findViewById(R.id.background_view);
        mVideoIndexNum = (TextView) findViewById(R.id.view_index_num);
        mViewTitle = (TextView) findViewById(R.id.music_tr_item_title);

    }

    private void setPagerData(int position) {
        if (null != mDataBeans && mDataBeans.size() > position) {
            //取出Card元素
            OpenEyesIndexItemBean indexItemBean = mDataBeans.get(position).getData().getContent().getData();
            mViewTitle.setText(indexItemBean.getTitle());
            mVideoIndexNum.setText((position + 1) + "/" + mDataBeans.size());
            if (null != mBackgroundLayout) {
                mBackgroundLayout.setBackgroundCover(indexItemBean.getCover().getBlurred(),
                        100, false, 8, false);
            }
        }
    }


    private class TransformerViewpager extends PagerAdapter {

        @Override
        public int getCount() {
            return mDataBeans == null ? 0 : mDataBeans.size() + 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TransformerMoiveItem item = new TransformerMoiveItem(container.getContext());
            position %= mDataBeans.size();
            item.setData(mDataBeans.get(position));
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            if (object instanceof TransformerMoiveItem) {
                TransformerMoiveItem moiveItem = (TransformerMoiveItem) object;
                moiveItem.onDestroy();
            }
            container.removeView((View) object);
        }

        @Override
        public void finishUpdate(@NonNull ViewGroup container) {
            super.finishUpdate(container);
            int currentItem = mViewPager.getCurrentItem();
            //如果已经滚动至第一个，则重新定位至data.size()+2-1的位置
            if (currentItem == 0) {
                currentItem = mDataBeans.size();
                //如果已经滚动至data.size()+2-1，则重新定位至第一个位置，保留左侧一个
            } else if (currentItem == (mDataBeans.size() + 2 - 1)) {
                currentItem = 1;
            }
            mViewPager.setCurrentItem(currentItem, false);
        }
    }

    /**
     * 更新data
     *
     * @param data          源数据
     * @param fiexdPosition 默认显示位置
     */
    public void setDatas(List<OpenEyesIndexItemBean> data, int fiexdPosition) {
        if (null != mAdapter && null != mAdapter) {
            if (null != mDataBeans) {
                mDataBeans.clear();
            }
            if (null == mDataBeans) mDataBeans = new ArrayList<>();
            mDataBeans.addAll(data);
            //在finishUpdate中实现无限循环的时，需将此值设置为data.size()+2,
            //以免finishUpdate中设置了setCurrentItem之后ViewPager的Item不加载
            mViewPager.setOffscreenPageLimit(data.size() + 2);
            mAdapter.notifyDataSetChanged();
            if (data.size() > fiexdPosition) {
                mViewPager.setCurrentItem(fiexdPosition);
            }
        }
        //更新Banner背景
        if (data.size() > fiexdPosition) {
            setPagerData(fiexdPosition);
        } else {
            setPagerData(0);
        }
    }


    /**
     * 对应生命周期调用
     */
    public void onDestroy() {
        if (null != mDataBeans) mDataBeans.clear();
        if (null != mAdapter) {
            mAdapter.notifyDataSetChanged();
        }
        if (null != mBackgroundLayout) {
            mBackgroundLayout.onDestroy();
            mBackgroundLayout = null;
        }
        if (null != mViewPager) {
            mViewPager.removeAllViews();
            mViewPager = null;
        }
    }
}
