package com.xhp.testutils.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.xhp.testutils.R;

public class RoundImageView extends AppCompatImageView {

    public static final int TYPE_CIRCLE = 0;
    public static final int TYPE_ROUND = 1;

    public static final int SCALE_TYPE_CROP = 0;
    public static final int SCALE_TYPE_FIT = 1;

    private static final int BODER_RADIUS_DEFAULT = 10;

    private static final String STATE_INSTANCE = "state_instance";
    private static final String STATE_TYPE = "state_type";
    private static final String STATE_BORDER_RADIUS = "state_border_radius";

    // 左上角是否为圆角
    private boolean corners_top_left = true;

    // 右上角是否为圆角
    private boolean corners_top_right = true;

    // 左下角是否为圆角
    private boolean corners_bottom_left = true;

    // 右下角是否为圆角
    private boolean corners_bottom_right = true;

    // 显示类型，可选项：圆形，圆角矩形
    private int type = TYPE_CIRCLE;

    // 填充类型，可选项：充满，剪裁
    private int scaleType = SCALE_TYPE_CROP;

    // 圆角半径
    private int borderRadius;

    private int width;
    private int radius;

    private Paint bitmapPaint;
    private RectF roundRect;
    private Matrix matrix;
    private BitmapShader bitmapShader;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        matrix = new Matrix();
        bitmapPaint = new Paint();
        bitmapPaint.setAntiAlias(true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        type = array.getInt(R.styleable.RoundImageView_image_type, TYPE_CIRCLE);
        radius = array.getInt(R.styleable.RoundImageView_image_borderRadius,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, BODER_RADIUS_DEFAULT,
                        getResources().getDisplayMetrics()));
        scaleType = array.getInt(R.styleable.RoundImageView_image_scale, SCALE_TYPE_FIT);
        corners_top_left = array.getBoolean(R.styleable.RoundImageView_image_top_left, true);
        corners_top_right = array.getBoolean(R.styleable.RoundImageView_image_top_right, true);
        corners_bottom_left = array.getBoolean(R.styleable.RoundImageView_image_bottom_left, true);
        corners_bottom_right = array.getBoolean(R.styleable.RoundImageView_image_bottom_right, true);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            radius = width / 2;
            setMeasuredDimension(width, width);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(STATE_TYPE, type);
        bundle.putInt(STATE_BORDER_RADIUS, borderRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.type = bundle.getInt(STATE_TYPE);
            this.borderRadius = bundle.getInt(STATE_BORDER_RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getDrawable() == null) {
            return;
        }
        setUpShader();
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(roundRect, borderRadius, borderRadius, bitmapPaint);
            if (!corners_top_left) {
                canvas.drawRect(0, 0, borderRadius, borderRadius, bitmapPaint);
            }
            if (!corners_top_right) {
                canvas.drawRect(roundRect.right - borderRadius, 0, roundRect.right, borderRadius, bitmapPaint);
            }
            if (!corners_bottom_left) {
                canvas.drawRect(0, roundRect.bottom - borderRadius, borderRadius, roundRect.bottom, bitmapPaint);
            }
            if (!corners_bottom_right) {
                canvas.drawRect(roundRect.right - borderRadius, roundRect.bottom -
                        borderRadius, roundRect.right, roundRect.bottom, bitmapPaint);
            }
        } else {
            canvas.drawCircle(width / 2, width / 2, radius, bitmapPaint);
        }
    }

    /**
     * 显示图片
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = drawToBitmap(drawable);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        bitmapPaint.setShader(bitmapShader);
        float scale = 1.0f;
        if (type == TYPE_CIRCLE) {
            scale = width * 1.0f / Math.min(bitmap.getWidth(), bitmap.getHeight());
            matrix.setScale(scale, scale);
        } else if (type == TYPE_ROUND) {
            float scaleWidth = getWidth() * 1.0f / bitmap.getWidth();
            float scaleHeight = getHeight() * 1.0f / bitmap.getHeight();
            scale = scaleWidth != scaleHeight ? Math.max(scaleWidth, scaleHeight) : 1f;
            if (scaleType == SCALE_TYPE_CROP) {
                matrix.setScale(scale, scale);
            } else if (scaleType == SCALE_TYPE_FIT) {
                matrix.setScale(scaleWidth, scaleHeight);
            }
        }
        bitmapShader.setLocalMatrix(matrix);
        bitmapPaint.setShader(bitmapShader);
    }

    private Bitmap drawToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
            return bitmap;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (type == TYPE_ROUND) {
            roundRect = new RectF(0, 0, w, h);
        }
    }
}
