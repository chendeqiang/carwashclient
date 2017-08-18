package com.example.carwashclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.example.carwashclient.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by chendeqiang on 2017/7/14
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class OrderListAdapter extends FragmentStatePagerAdapter {
     private List<FragmentInfo> mShowItems=new ArrayList<>();

    public OrderListAdapter(FragmentManager fm, List<FragmentInfo> showItems) {
        super(fm);
        mShowItems = showItems;
    }

    @Override
    public Fragment getItem(int position) {
        return mShowItems.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mShowItems.get(position).title;
    }
}
