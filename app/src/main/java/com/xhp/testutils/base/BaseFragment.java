package com.xhp.testutils.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xhp.testutils.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getSimpleName();

    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    private boolean fragmentIsFirstVisible = true;

    protected Activity mActivity;
    protected ViewGroup rootView;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisible();
            isFragmentVisible = true;
            if (fragmentIsFirstVisible) {
                onFragmentFirstVisible();
                fragmentIsFirstVisible = false;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        rootView = (ViewGroup) inflater.inflate(getLayoutResId(), container, false);
        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentIsFirstVisible = true;
    }

    protected void initToolbar(Toolbar toolbar, CharSequence title, boolean showBackBtn) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) mActivity;
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(showBackBtn);
            if (showBackBtn) {
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        }
    }

    /**
     * 实现Fragment数据的缓加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisible();
            isFragmentVisible = true;
            if (fragmentIsFirstVisible) {
                onFragmentFirstVisible();
                fragmentIsFirstVisible = false;
            }
            return;
        }
        if (isFragmentVisible) {
            onFragmentGone();
            isFragmentVisible = false;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    /**
     * 如果需要用到懒加载，可以使用此方法加载数据
     */
    protected void onFragmentVisible() {
    }

    /**
     * Fragment第一次显示
     */
    protected void onFragmentFirstVisible() {
    }

    /**
     * Fragment不可见时调用此方法
     */
    protected void onFragmentGone() {
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();


    @Nullable
    @Override
    public Context getContext() {
        return mActivity;
    }

    private Map<Short, Runnable> requestPermissionMap = new HashMap<>();


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Set<Short> requestCodeSet = requestPermissionMap.keySet();
        if (!requestCodeSet.isEmpty()) {
            for (Short key : requestCodeSet) {
                if (requestCode == (int) key) {
                    boolean get = true;
                    for (int i : grantResults) {
                        if (i != PackageManager.PERMISSION_GRANTED) {
                            get = false;
                            break;
                        }
                    }
                    if (get) {
                        Runnable runnable = requestPermissionMap.get(key);
                        if (runnable != null) {
                            mHandler.post(runnable);
                        }
                        requestPermissionMap.remove(key);
                        break;
                    }
                }
            }
        }
    }

    private short getRequestCode() {
        short requestCode = 1963;
        Set<Short> requestCodeSet = requestPermissionMap.keySet();
        if (!requestCodeSet.isEmpty()) {
            while (requestCodeSet.contains(requestCode)) {
                requestCode = (short) (Math.random() * 100);
            }
        }
        return requestCode;
    }
}
