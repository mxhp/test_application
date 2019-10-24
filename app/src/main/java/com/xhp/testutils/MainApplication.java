package com.xhp.testutils;

import android.app.Application;

import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setPlayerFactory(IjkPlayerFactory.create())
                .setEnableOrientation(true)
                .setLogEnabled(true)
                .build());
    }

}
