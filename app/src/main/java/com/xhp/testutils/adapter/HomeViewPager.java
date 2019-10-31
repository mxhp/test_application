package com.xhp.testutils.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeViewPager extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();

    public HomeViewPager(FragmentManager fm) {
        super(fm);
    }

    public void setFragmentList(List<Fragment> fragments) {
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }
}
