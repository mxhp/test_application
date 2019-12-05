package com.xhp.testutils.view;

import android.content.Context;
import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xhp.testutils.R;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.util.DisplayUtils;

public class TransformerMoiveItem extends FrameLayout {

    private ImageView mRoundImageView;

    public void setData(OpenEyesIndexItemBean item) {
        if (null != item && null != item.getData()) {
            OpenEyesIndexItemBean data = item.getData().getContent().getData();
//            TextView tvDurtion = (TextView) findViewById(R.id.music_tr_item_durtion);
//            tvDurtion.setText(MusicUtils.getInstance().stringForAudioTime(data.getDuration()*1000));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // 设置阴影外框
                findViewById(R.id.view_root_item).setOutlineProvider(
                        new ViewOutlineProvider() {

                            @Override
                            public void getOutline(View view, Outline outline) {
                                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), DisplayUtils.dp2px(8, getContext()));
                                view.setClipToOutline(true);
                            }
                        });
            }
            Glide.with(getContext())
                    .load(data.getCover().getFeed())
                    .placeholder(R.drawable.ic_video_default_cover)
                    .error(R.drawable.ic_video_default_cover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mRoundImageView);
            this.setTag(data);
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != view.getTag()) {
//                        OpenEyesIndexItemBean indexItemBean = (OpenEyesIndexItemBean) view.getTag();
//                        Intent intent = new Intent(getContext().getApplicationContext(), VideoPlayerActviity.class);
//                        VideoParams videoParams = MediaUtils.getInstance().formatVideoParams(indexItemBean);
//                        intent.putExtra(VideoConstants.KEY_VIDEO_PARAMS, videoParams);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getContext().getApplicationContext().startActivity(intent);
                    }
                }
            });
        }
    }

    public TransformerMoiveItem(@NonNull Context context) {
        this(context, null);
    }

    public TransformerMoiveItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.video_pager_item, this);
        mRoundImageView = (ImageView) findViewById(R.id.music_tr_item_cover);
    }

    public void onDestroy() {
        if (null != mRoundImageView) {
            mRoundImageView.setImageResource(0);
            mRoundImageView = null;
        }
    }
}
