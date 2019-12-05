package com.xhp.testutils.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xhp.testutils.R;
import com.xhp.testutils.adapter.viewholder.CardBanner;
import com.xhp.testutils.adapter.viewholder.UnKnownView;
import com.xhp.testutils.adapter.viewholder.VideoBannerViewHolder;
import com.xhp.testutils.adapter.viewholder.VideoTitleViewHolder;
import com.xhp.testutils.adapter.viewholder.VideoVideoViewHolder;
import com.xhp.testutils.amator.UserGlideCircleTransform;
import com.xhp.testutils.bean.OpenEyesIndexItemBean;
import com.xhp.testutils.util.DisplayUtils;
import com.xhp.testutils.util.ImageUtil;
import com.xhp.testutils.view.VideoTextrueProvider;

public class KaiYanHomeAdapter extends CommonMutiType4RecyclerView<OpenEyesIndexItemBean, RecyclerView.ViewHolder> {

    private final int mScreenWidth;
    private final OnItemClickListener mItemClickListener;

    public KaiYanHomeAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        super(mContext);
        mItemClickListener = onItemClickListener;
        mScreenWidth = DisplayUtils.getScreenWidth(mContext);
    }

    @Override
    protected RecyclerView.ViewHolder CreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == OpenEyesIndexItemBean.ITEM_CARD) {
            View inflate = mInflater.inflate(R.layout.video_index_video_card, null);
            return new CardBanner(inflate);
        } else if (viewType == OpenEyesIndexItemBean.ITEM_TITLE) {
            View inflate = mInflater.inflate(R.layout.video_index_video_title, null);
            return new VideoTitleViewHolder(inflate);
        } else if (viewType == OpenEyesIndexItemBean.ITEM_FOLLOW) {
            View inflate = mInflater.inflate(R.layout.video_index_video_video, null);
            return new VideoVideoViewHolder(inflate);
        } else if (viewType == OpenEyesIndexItemBean.ITEM_VIDEO) {
            View inflate = mInflater.inflate(R.layout.video_index_video_video, null);
            return new VideoVideoViewHolder(inflate);
        } else if (viewType == OpenEyesIndexItemBean.ITEM_NOIMAL) {
            View inflate = mInflater.inflate(R.layout.video_index_video_video, null);
            return new VideoVideoViewHolder(inflate);
        } else if (viewType == OpenEyesIndexItemBean.ITEM_BANNER) {
            View inflate = mInflater.inflate(R.layout.video_index_video_banner, null);
            return new VideoBannerViewHolder(inflate);
        }
        return new UnKnownView(mInflater.inflate(R.layout.music_unknown_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        viewHolder.itemView.setTag(null);
        int itemViewType = getItemViewType(position);
        OpenEyesIndexItemBean itemData = getItemData(position);
        if (null != itemData) {
            //精品推荐
            if (itemViewType == OpenEyesIndexItemBean.ITEM_CARD) {
                CardBanner cardViewHolder = (CardBanner) viewHolder;
                if (null != itemData.getData()) {
                    cardViewHolder.mTransformerVideoPager.setDatas(itemData.getData().getItemList(), 0);
                }
                //标题
            } else if (itemViewType == OpenEyesIndexItemBean.ITEM_TITLE) {
                VideoTitleViewHolder titleViewHolder = (VideoTitleViewHolder) viewHolder;
                if (null != itemData.getData()) {
                    itemData.getData().setItemType(OpenEyesIndexItemBean.ITEM_TITLE);
                    viewHolder.itemView.setTag(itemData.getData());
                    //区别不同的标题
                    if (itemData.getData().getType().equals("footer2")) {
                        titleViewHolder.rootTitle.setVisibility(View.GONE);
                        titleViewHolder.rootTitle1.setVisibility(View.VISIBLE);
                        titleViewHolder.textTitle2.setText(itemData.getData().getText() + ">");
                    } else {
                        titleViewHolder.rootTitle1.setVisibility(View.GONE);
                        titleViewHolder.rootTitle.setVisibility(View.VISIBLE);
                        titleViewHolder.textTitle.setText(itemData.getData().getText());
                    }
                    titleViewHolder.rootTitle1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (null != mItemClickListener) {
                                mItemClickListener.onItemClick(viewHolder.itemView, position, 0);
                            }
                        }
                    });
                }
                //推荐视频
            } else if (itemViewType == OpenEyesIndexItemBean.ITEM_FOLLOW) {
                VideoVideoViewHolder videoViewHolder = (VideoVideoViewHolder) viewHolder;
                if (null != itemData.getData() && null != itemData.getData().getContent()) {
                    OpenEyesIndexItemBean indexItemBean = itemData.getData().getContent().getData();
                    setItemVideoData(videoViewHolder, indexItemBean, position);
                }
                //视频
            } else if (itemViewType == OpenEyesIndexItemBean.ITEM_VIDEO) {
                VideoVideoViewHolder videoViewHolder = (VideoVideoViewHolder) viewHolder;
                if (null != itemData.getData()) {
                    OpenEyesIndexItemBean indexItemBean = itemData.getData();
                    setItemVideoData(videoViewHolder, indexItemBean, position);
                }
                //播放器界面相关推荐
            } else if (itemViewType == OpenEyesIndexItemBean.ITEM_NOIMAL) {
                VideoVideoViewHolder videoViewHolder = (VideoVideoViewHolder) viewHolder;
                setItemVideoData(videoViewHolder, itemData, position);
                //Banner
            } else if (itemViewType == OpenEyesIndexItemBean.ITEM_BANNER) {
                VideoBannerViewHolder bannerViewHolder = (VideoBannerViewHolder) viewHolder;
                if (null != itemData.getData()) {
                    int itemHeight = (mScreenWidth - DisplayUtils.dp2px(30f, mContext)) * 9 / 16;
                    bannerViewHolder.itemBannerCover.getLayoutParams().height = itemHeight;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        bannerViewHolder.itemBannerRoot.setOutlineProvider(new VideoTextrueProvider(
                                DisplayUtils.dp2px(8f, mContext)));
                    }
                    OpenEyesIndexItemBean itemDataData = itemData.getData();
                    itemDataData.setItemType(OpenEyesIndexItemBean.ITEM_BANNER);
                    bannerViewHolder.itemView.setTag(itemDataData);
                    if (itemData.getType().equals("banner2")) {
                        bannerViewHolder.itemBannerTag.setText("活动");
                    } else {
                        bannerViewHolder.itemBannerTag.setText("广告");
                    }
                    Glide.with(mContext)
                            .load(ImageUtil.getInstance().formatImageUrl(itemDataData.getImage()))
                            .placeholder(R.drawable.ic_video_default_cover)
                            .error(R.drawable.ic_video_default_cover)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(bannerViewHolder.itemBannerCover);
                }
            }
        }

    }

    /**
     * 更新视频条目数据
     *
     * @param videoViewHolder
     * @param indexItemBean
     */
    private void setItemVideoData(final VideoVideoViewHolder videoViewHolder,
                                  OpenEyesIndexItemBean indexItemBean, final int position) {
        int itemHeight = (mScreenWidth - DisplayUtils.dp2px(30f, mContext)) * 9 / 16;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            videoViewHolder.itemRoomItem.setOutlineProvider(new VideoTextrueProvider(
                    DisplayUtils.dp2px(8f, mContext)));
        }
        videoViewHolder.trackVideo.getLayoutParams().height = itemHeight;
        //这里将条目View抛出，界面处理无缝衔接播放
        videoViewHolder.itemView.setTag(indexItemBean);
        videoViewHolder.menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemClick(videoViewHolder.itemView, position, 0);
                }
            }
        });
        videoViewHolder.trackVideo.setVideoController(videoViewHolder.controller);
        videoViewHolder.trackVideo.setUrl(indexItemBean.getPlayUrl());
        videoViewHolder.controller.setTitle(indexItemBean.getTitle());
        videoViewHolder.itemMenu.setTag(null);
        if (!TextUtils.isEmpty(indexItemBean.getDescription())) {
            videoViewHolder.itemMenu.setVisibility(View.VISIBLE);
            videoViewHolder.itemMenu.setTag(indexItemBean);
            videoViewHolder.itemMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mOnMenuClickListener) {
                        mOnMenuClickListener.onMenuClick(videoViewHolder.itemView, v);
                    }
                }
            });
        } else {
            videoViewHolder.itemMenu.setVisibility(View.INVISIBLE);
        }
        videoViewHolder.itemTitle.setText(indexItemBean.getTitle());
        if (null != indexItemBean.getCover() && null != videoViewHolder.controller) {
            //封面
            Glide.with(mContext)
                    .load(indexItemBean.getCover().getFeed())
                    .placeholder(R.drawable.ic_video_default_cover)
                    .error(R.drawable.ic_video_default_cover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(videoViewHolder.controller.getThumb());
        }
        if (null != indexItemBean.getAuthor()) {
            //用户头像
            Glide.with(mContext)
                    .load(indexItemBean.getAuthor().getIcon())
                    .error(R.drawable.ic_music_default_cover)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new UserGlideCircleTransform(mContext))
                    .into(videoViewHolder.itemUserCover);
        } else {
            videoViewHolder.itemUserCover.setImageResource(0);
        }
    }

    public OpenEyesIndexItemBean getItemData(int position) {
        if (position < 0 || getData().size() == 0 || getData().size() < position) {
            return null;
        }
        return mData.get(position);
    }

    public interface OnMenuClickListener {
        void onMenuClick(View itemView, View view);
    }

    private OnMenuClickListener mOnMenuClickListener;

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        mOnMenuClickListener = onMenuClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int posotion, long musicID);
    }

    @Override
    public int getItemViewType(int position) {
        if (getData() != null) {
            return getData().get(position).getItemType();
        }
        return OpenEyesIndexItemBean.ITEM_UNKNOWN;
    }
}
