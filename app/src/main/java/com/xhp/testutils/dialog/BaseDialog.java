package com.xhp.testutils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.xhp.testutils.R;

public abstract class BaseDialog extends Dialog {

    protected Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.CenterDialogAnimationStyle);
        mContext=context;
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context,themeResId);
        mContext=context;
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        initViews();
    }
    public abstract void initViews();

    /**
     * 设置Dialog依附在屏幕中的位置
     */
    protected void initLayoutParams(int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();//得到布局管理者
        WindowManager systemService = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);//得到窗口管理者
        DisplayMetrics displayMetrics=new DisplayMetrics();//创建设备屏幕的管理者
        systemService.getDefaultDisplay().getMetrics(displayMetrics);//得到屏幕的宽高
        int hight= LinearLayout.LayoutParams.WRAP_CONTENT;//取出布局的高度
        attributes.height= hight;
        attributes.width= systemService.getDefaultDisplay().getWidth();
        attributes.gravity= gravity;
    }


    /**
     * 设置Dialog依附在屏幕中的位置
     */
    protected void initLayoutMarginParams(int gravity) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();//得到布局管理者
        WindowManager systemService = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);//得到窗口管理者
        DisplayMetrics displayMetrics=new DisplayMetrics();//创建设备屏幕的管理者
        systemService.getDefaultDisplay().getMetrics(displayMetrics);//得到屏幕的宽高
        int hight= LinearLayout.LayoutParams.WRAP_CONTENT;//取出布局的高度
        attributes.height= hight;
        attributes.width= systemService.getDefaultDisplay().getWidth()-120;
        attributes.gravity= gravity;
    }


    @Override
    public void dismiss() {
        super.dismiss();
        mContext=null;
    }
}
