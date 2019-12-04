package com.xhp.testutils.adapter.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xhp.testutils.R;
import com.xhp.testutils.view.TransformerVideoPager;

public class CardBanner extends RecyclerView.ViewHolder {
    public TransformerVideoPager mTransformerVideoPager;

    public CardBanner(@NonNull View itemView) {
        super(itemView);
        mTransformerVideoPager = (TransformerVideoPager) itemView.findViewById(R.id.trans_video);
    }
}
