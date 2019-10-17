package com.xhp.testutils;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SurfacePlayerActivity extends AppCompatActivity {

    private SurfaceView mSurfaceview;
    private MediaPlayer mMediaPlayer;
    private SurfaceHolder mHolder;
    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
    private SurfaceView mSurfaceview1;
    private SurfaceHolder mHolder1;
    private Button mMagic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_player);
        initViews();
    }


    private void initViews() {
        mSurfaceview = findViewById(R.id.surfaceview);
        mHolder = mSurfaceview.getHolder();
        Log.i("test",mHolder.toString());
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initMediaPlayer();
                // 不设置这个会导致 只有声音 却没有画面的现象
                mMediaPlayer.setDisplay(holder);
                Log.i("test","surfaceCreated"+holder.toString());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releasePlayer();
            }
        });
        mSurfaceview1 = findViewById(R.id.surfaceview1);
        mHolder1 = mSurfaceview1.getHolder();

        mHolder1.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
//                initMediaPlayer();
                // 不设置这个会导致 只有声音 却没有画面的现象
//                mMediaPlayer.setDisplay(holder);
                Log.i("test","mHolder1 ok");
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releasePlayer();
            }
        });
        mMagic = findViewById(R.id.magic);
        mMagic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMagic();
            }
        });
    }

    private void doMagic() {
        initMediaPlayer();
        mMediaPlayer.setDisplay(mHolder1);
    }

    private void initMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });

        }
        try {
            if (!mMediaPlayer.isPlaying()) {
                mMediaPlayer.setDataSource(videoUrl);
                mMediaPlayer.prepareAsync();
            }else {
                mMediaPlayer.stop();
                mMediaPlayer.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void releasePlayer() {
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
