package com.xhp.testutils.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xhp.testutils.R;
import com.xhp.testutils.util.ImageUtil;

public class MusicJukeBoxBackgroundLayout extends RelativeLayout {
    private Context mContext;
    private int DURATION_ANIMATION = 500;
    private ObjectAnimator objectAnimator;
    //背景,前景图层
    private ImageView mImageViewBg, mImageViewFg;
    //屏幕宽高
    private int mScreenWidth, mScreenHeight;

    //任务Runnable
    private SetBackgroundRunnable mBackgroundRunnable;

    public MusicJukeBoxBackgroundLayout(Context context) {
        this(context, null);
    }

    public MusicJukeBoxBackgroundLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicJukeBoxBackgroundLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        //背景图片
        mImageViewBg = new ImageView(context);
        mImageViewBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageViewBg.setImageResource(R.drawable.default_bg);
        addView(mImageViewBg, new RelativeLayout.LayoutParams(-1, -1));
        // 前景图片
        mImageViewFg = new ImageView(context);
        mImageViewFg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        addView(mImageViewFg, new RelativeLayout.LayoutParams(-1, -1));
        //  屏幕宽高
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
        // 属性
        boolean isEnable = false;
        if (null != attrs) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicJukeBoxBackgroundLayout);
            isEnable = typedArray.getBoolean(R.styleable.MusicJukeBoxBackgroundLayout_backgroundEnable, false);
            typedArray.recycle();
        }
        if (isEnable) {
            initObjectAnimator();
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private void initObjectAnimator() {
        if (null == objectAnimator) {
            objectAnimator = ObjectAnimator.ofFloat(this, "number", 0f, 1f);
            objectAnimator.setDuration(DURATION_ANIMATION);
            objectAnimator.setInterpolator(new AccelerateInterpolator());
            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    if (mImageViewFg != null) {
                        int alphavalue = (int) ((float) animation.getAnimatedValue() * 255);
                        mImageViewFg.getDrawable().setAlpha(alphavalue);
                    }
                }
            });
            objectAnimator.addListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    if (mImageViewFg != null && mImageViewFg.getDrawable() != null && mImageViewBg != null) {
                        mImageViewBg.setImageDrawable(mImageViewFg.getDrawable());
                    }
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    private void startGradualAnimator() {
        if (objectAnimator != null) {
            objectAnimator.start();
        }
    }

    /**
     * 设置背景封面
     *
     * @param imageUrl    图片url
     * @param delayMillis 延时的时长
     */
    public void setBackgroundCover(String imageUrl, long delayMillis) {
        setBackgroundCover(imageUrl, delayMillis, true);
    }

    /**
     * 设置背景封面
     *
     * @param imageUrl    图片url
     * @param delayMillis 延时的时长
     * @param isBlur      背景是否虚化
     */
    private synchronized void setBackgroundCover(String imageUrl, long delayMillis, boolean isBlur) {
        setBackgroundCover(imageUrl, delayMillis, isBlur, 5);
    }

    /**
     * 设置背景封面
     *
     * @param imageUrl    图片url
     * @param delayMillis 延时的时长
     * @param isBlur      背景是否虚化
     * @param blurRadius  虚化的角度
     */
    private synchronized void setBackgroundCover(String imageUrl, long delayMillis, boolean isBlur, int blurRadius) {
        setBackgroundCover(imageUrl, delayMillis, isBlur, blurRadius, true);
    }

    /**
     * 设置背景封面
     *
     * @param imageUrl    图片url
     * @param delayMillis 延时的时长
     * @param isBlur      背景是否虚化
     * @param blurRadius  虚化的角度
     * @param shadeEnable 是否启用遮罩层图层
     */
    private synchronized void setBackgroundCover(String imageUrl, long delayMillis, boolean isBlur, int blurRadius, boolean shadeEnable) {
        if (null != mBackgroundRunnable && !TextUtils.isEmpty(mBackgroundRunnable.getmImageUrl()) &&
                mBackgroundRunnable.getmImageUrl().equals(imageUrl)) {
            return;
        }
        if (null != mBackgroundRunnable) {
            mBackgroundRunnable.reset();
            MusicJukeBoxBackgroundLayout.this.removeCallbacks(mBackgroundRunnable);
            mBackgroundRunnable = null;
        }
        mBackgroundRunnable = new SetBackgroundRunnable(isBlur, shadeEnable, imageUrl, blurRadius);
        MusicJukeBoxBackgroundLayout.this.postDelayed(mBackgroundRunnable, delayMillis);
    }

    /**
     * 设置前景图片
     *
     * @param bitmap
     */
    public void setForeground(Bitmap bitmap) {
        if (mImageViewFg != null) {
            mImageViewFg.setImageBitmap(bitmap);
            startGradualAnimator();
        }
    }

    /**
     * 设置前景图片
     *
     * @param drawable
     */
    public void setForeground(Drawable drawable) {
        if (null != mImageViewFg) {
            mImageViewFg.setImageDrawable(drawable);
            startGradualAnimator();
        }
    }

    public void onDestroy() {
        if (mBackgroundRunnable != null) {
            mBackgroundRunnable.reset();
            MusicJukeBoxBackgroundLayout.this.removeCallbacks(mBackgroundRunnable);
            mBackgroundRunnable = null;
        }
        if (objectAnimator != null) {
            objectAnimator.cancel();
            objectAnimator = null;
        }
        recycleImage(mImageViewBg);
        recycleImage(mImageViewFg);
        objectAnimator = null;
        mImageViewBg = mImageViewFg = null;
        DURATION_ANIMATION = mScreenWidth = mScreenHeight = 0;
        mContext = null;
    }

    private void recycleImage(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            imageView.setImageBitmap(null);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
        } else {
            imageView.setImageBitmap(null);
        }
    }


    private class SetBackgroundRunnable implements Runnable {

        //Bitmap是否虚化处理
        private final boolean mIsBlur;
        //遮罩图层默认是开启的
        private boolean mShadeEnable = true;
        //图片URL
        private String mImageUrl;
        //虚化半径
        private int mBlurRadius = 5;

        public SetBackgroundRunnable(boolean mIsBlur, boolean mShadeEnable, String mImageUrl, int mBlurRadius) {
            this.mIsBlur = mIsBlur;
            this.mShadeEnable = mShadeEnable;
            this.mImageUrl = mImageUrl;
            this.mBlurRadius = mBlurRadius;
        }

        public String getmImageUrl() {
            return mImageUrl;
        }

        public void reset() {
            mImageUrl = null;
        }

        @Override
        public void run() {
            if (!TextUtils.isEmpty(mImageUrl) && mImageViewFg != null) {
                if (mImageUrl.startsWith("http:") || mImageUrl.startsWith("https:")) {
                    Glide.with(getContext())
                            .load(mImageUrl)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    if (mImageViewFg != null) {
                                        if (mIsBlur) {
                                            Drawable drawable;
                                            if (mShadeEnable) {
                                                drawable = ImageUtil.getInstance().getBlurBitmap(resource, mScreenWidth, mScreenHeight, mBlurRadius, Color.parseColor("#FF999999"));
                                            } else {
                                                drawable = ImageUtil.getInstance().getBlurBitmap(resource, mScreenWidth, mScreenHeight, mBlurRadius, Color.parseColor("#00000000"));
                                            }
                                            mImageViewFg.setImageDrawable(drawable);
                                        } else {
                                            mImageViewFg.setImageBitmap(resource);
                                        }
                                        startGradualAnimator();
                                    }
                                }

                                @Override
                                public void onLoadFailed(Exception e, Drawable errorDrawable) {
                                    if (null != mImageViewFg) {
                                        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.default_bg);
                                        mImageViewFg.setImageDrawable(drawable);
                                        startGradualAnimator();
                                    }
                                }
                            });
                } else {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_bg);
                    Drawable foregroundDrawable = ImageUtil.getInstance().getBlurBitmap(bitmap, mScreenWidth, mScreenHeight, mBlurRadius, Color.parseColor("#FF999999"));
                    if (foregroundDrawable == null) {
                        Context context;
                        foregroundDrawable = ContextCompat.getDrawable(getContext(), R.drawable.default_bg);
                    }
                    mImageViewFg.setImageDrawable(foregroundDrawable);
                    startGradualAnimator();
                }
            }
        }
    }
}
