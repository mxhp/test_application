package com.xhp.testutils;

import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class PlayerActvity extends AppCompatActivity {
    private static final String TAG = "PlayerActvity";
    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;
    private Surface mSurface;
    //http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
    private String videoUrl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_actvity);
        initViews();
    }

    private void intMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //设置视频画面显示位置
            mMediaPlayer.setSurface(mSurface);

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.e(TAG, "onPrepared");
                    mp.start();
                }
            });
            mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.d(TAG, "onBufferingUpdate percent=" + percent);
                }
            });
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.e(TAG, "onCompletion ");
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.e(TAG, "onError what= " + what + " extra=" + extra);
                    return false;
                }
            });
            mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    Log.e(TAG, "onInfo what= " + what + " extra=" + extra);
                    return false;
                }
            });
        } else {
            mMediaPlayer.reset();
        }

    }

    private void initViews() {
        mTextureView = findViewById(R.id.texture_view);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                Log.e(TAG, "onSurfaceTextureAvailable");
                mSurface = new Surface(surface);
                setDataAndPlay();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                Log.e(TAG, "onSurfaceTextureSizeChanged");
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                Log.e(TAG, "onSurfaceTextureDestroyed");
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = null;
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//                Log.e(TAG, "onSurfaceTextureUpdated");
            }
        });

    }

    private void setDataAndPlay() {
        if (mMediaPlayer == null) {
            intMediaPlayer();
        }
        try {
            mMediaPlayer.setDataSource(PlayerActvity.this, Uri.parse(videoUrl));
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
