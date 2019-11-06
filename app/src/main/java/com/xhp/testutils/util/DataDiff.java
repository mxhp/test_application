package com.xhp.testutils.util;

import android.support.v7.util.DiffUtil;

import com.xhp.testutils.bean.Category;

import java.util.List;

public class DataDiff extends DiffUtil.Callback {
    List<Category.ResultsBean> current , next ;

    public DataDiff(List<Category.ResultsBean> current, List<Category.ResultsBean> next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public int getOldListSize() {
        return current==null?0 :current.size();
    }

    @Override
    public int getNewListSize() {
        return next==null?0 :next.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return current.get(i).equals(next.get(i1));
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return current.get(i).equals(next.get(i1));
    }
}
