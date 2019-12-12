package com.xhp.testutils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;


public class PaintTestView extends View {
    private Paint mPaint;
    private Path mPath;

    public PaintTestView(Context context) {
        this(context, null);
    }

    public PaintTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public PaintTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.BLUE);
//        mPath = new Path();
//        mPath.lineTo(30, 30);
//        mPath.quadTo(120, 120, 520, 520);
//        mPath.moveTo(500, 100); // 我移~~
//        mPath.lineTo(100, 100); // 画斜线
//        mPath.moveTo(200, 100); // 我移~~
//        mPath.lineTo(200, 0); // 画竖线
//        mPath.arcTo(100,100,900,900,-90,45,false);
//        mPath.close();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
//        canvas.drawCircle(200, 100, 100, mPaint);
//        mPaint.setStrokeWidth(50);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
//        canvas.drawPoint(200, 100, mPaint);
//        mPaint.setStrokeWidth(5);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawRoundRect(100, 100, 300, 200, 30, 20, mPaint);
//        mPaint.setStrokeWidth(10);
//        mPaint.setColor(Color.YELLOW);
//        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points, mPaint);

        mPaint.setStyle(Paint.Style.STROKE); // 填充模式
        mPaint.setStrokeWidth(5);
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, mPaint); // 绘制扇形
//        canvas.drawArc(200, 100, 800, 500, 20, 70, false, mPaint); // 绘制弧形
//        mPaint.setStyle(Paint.Style.STROKE); // 画线模式
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(75);
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, mPaint); // 绘制不封口的弧形
//        canvas.drawPath(mPath, mPaint);
        canvas.drawText("HenCoder",100,100,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
