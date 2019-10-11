package com.xhp.testutils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class SurfacePlayerActivity extends AppCompatActivity {

    private SurfaceView mSurfaceview;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mHolder;
    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_player);
        initViews();
    }



    private void initViews() {
        mSurfaceview = findViewById(R.id.surfaceview);
        mHolder = mSurfaceview.getHolder();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                 initMediaPlayer();
                 // 不设置这个会导致 只有声音 却没有画面的现象
                mMediaPlayer.setDisplay(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releasePlayer();
            }
        });
    }

    private void initMediaPlayer() {
        if (mMediaPlayer==null){
            mMediaPlayer =new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        }
        try {
            mMediaPlayer.setDataSource(videoUrl);
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void releasePlayer() {
        if (mMediaPlayer!=null){
            if (mMediaPlayer.isPlaying()){
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
