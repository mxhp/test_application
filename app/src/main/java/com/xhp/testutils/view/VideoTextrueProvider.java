package com.xhp.testutils.view;

import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class VideoTextrueProvider  extends ViewOutlineProvider {

    private float mRadius;

    public VideoTextrueProvider(float radius){
        this.mRadius = radius;
    }
    @Override
    public void getOutline(View view, Outline outline) {

    }
}
