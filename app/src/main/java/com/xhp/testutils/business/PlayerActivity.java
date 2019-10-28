package com.xhp.testutils.business;

import android.content.Context;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.xhp.testutils.R;
import com.xhp.testutils.base.BaseActivity;
import com.xhp.testutils.presenter.BasePresenter;

public class PlayerActivity extends BaseActivity {

    private VideoView videoView;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    protected void initView() {
        videoView = findViewById(R.id.videoview);
    }

    @Override
    protected void initData(Context context) {
        videoView.setUrl("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"); //设置视频地址
        StandardVideoController controller = new StandardVideoController(this);
        controller.setTitle("网易公开课-如何掌控你的自由时间"); //设置视频标题
        videoView.setVideoController(controller); //设置控制器，如需定制可继承BaseVideoController
        videoView.start(); //开始播放，不调用则不自动播放
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.release();
    }


    @Override
    public void onBackPressed() {
        if (!videoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

}
