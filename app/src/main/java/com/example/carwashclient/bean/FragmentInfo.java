package com.example.carwashclient.bean;

import android.support.v4.app.Fragment;

/**
 * 作者：Created by chendeqiang on 2017/7/18
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class FragmentInfo {
    public Fragment fragment;
    public String title;

    public FragmentInfo(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }
}
