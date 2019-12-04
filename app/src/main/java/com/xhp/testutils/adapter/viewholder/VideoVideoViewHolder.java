package com.xhp.testutils.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.xhp.testutils.R;
import com.xhp.testutils.view.RoundImageView;

public class VideoVideoViewHolder extends RecyclerView.ViewHolder {
    public RoundImageView itemUserCover;
    public TextView itemTitle;
    public ImageView itemMenu;
    public VideoView trackVideo;
    public View menuLayout;
    public LinearLayout itemRoomItem;
    public StandardVideoController controller;

    public VideoVideoViewHolder(@NonNull View itemView) {
        super(itemView);
        itemUserCover = (RoundImageView) itemView.findViewById(R.id.video_item_user_cover);
        itemTitle = (TextView) itemView.findViewById(R.id.view_item_title);
        itemMenu = (ImageView) itemView.findViewById(R.id.view_item_menu);
        trackVideo = (VideoView) itemView.findViewById(R.id.video_track);
        menuLayout = (View) itemView.findViewById(R.id.ll_menu_layout);
        itemRoomItem = (LinearLayout) itemView.findViewById(R.id.view_root_item);
        controller = new StandardVideoController(itemView.getContext());
    }
}
