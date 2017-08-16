package com.example.carwashclient.bean;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.carwashclient.R;
import com.example.carwashclient.ui.fragment.AddFragment;
import com.example.carwashclient.ui.fragment.CarFragment;
import com.example.carwashclient.ui.fragment.HomeFragment;
import com.example.carwashclient.ui.fragment.MineFragment;
import com.example.carwashclient.ui.fragment.OrderFragment;

/**
 * 作者:
 * 描述:枚举封闭数据
 *
 */

public enum MainTabs {
    HOME("HOME", "首页", R.drawable.tab_menu_home, HomeFragment.class, null),
    ORDER("ORDER", "订单", R.drawable.tab_menu_order, OrderFragment.class, null),
    ADD("ADD", "服务", R.drawable.tab_icon_selector, AddFragment.class, null),
    CAR("CAR", "爱车", R.drawable.tab_menu_car, CarFragment.class, null),
    ME("ME", "我的", R.drawable.tab_menu_mine, MineFragment.class, null);

    public String          mTag;
    public String          mTitle;
    public int             mImage;
    public Class<? extends Fragment> mClss;
    public Bundle          mBundle;

    MainTabs(String tag, String title, int image, Class<? extends Fragment> clss, Bundle bundle) {
        mTag = tag;
        mTitle = title;
        mImage = image;
        mClss = clss;
        mBundle = bundle;
    }
}
