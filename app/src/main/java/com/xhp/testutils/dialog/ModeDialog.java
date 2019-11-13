package com.xhp.testutils.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.xhp.testutils.R;
import com.xhp.testutils.TestActivity;
import com.xhp.testutils.TestAdater;

import java.util.Arrays;

import es.dmoral.toasty.Toasty;

public class ModeDialog extends BaseDialog implements TestAdater.OnclikCallBack {

    private RecyclerView mModeList;
    private Context mContext;
    private TestAdater testAdater;

    public ModeDialog(@NonNull Context context) {
        super(context, R.style.CenterDialogAnimationStyle);
        mContext = context;
        setContentView(R.layout.mode_layout);
    }


    @Override
    public void initViews() {
        mModeList = findViewById(R.id.mode_list);
        if (mModeList != null) {
            mModeList.setLayoutManager(new GridLayoutManager(mContext, 1, GridLayoutManager.HORIZONTAL, false));
        }
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(mContext, 30, 0, 0, 0);
        mModeList.addItemDecoration(spaceItemDecoration);
        testAdater = new TestAdater(mContext);
        testAdater.setOnclikCallBack(this);
        String[] strings = {"one", "two", "three", "four", "five", "one", "two", "three", "four", "five", "one", "two", "three", "four", "five"};
        testAdater.setDatas(Arrays.asList(strings));
        mModeList.setAdapter(testAdater);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onclick(int postion, String messageBean) {
        Toasty.normal(mContext, "posiion=" + postion + " message=" + messageBean).show();
        if (isShowing()) {
            dismiss();
        }
    }
}
