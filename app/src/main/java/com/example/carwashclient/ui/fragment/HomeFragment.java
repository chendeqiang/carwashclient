package com.example.carwashclient.ui.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * 作者：Created by chendeqiang on 2017/8/16
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class HomeFragment extends BaseFragment {
    @Override
    public Object getData() {
        return "";
    }

    @Override
    protected View createView() {
        TextView textView=new TextView(getContext());
        textView.setText("首页");
        return textView;
    }
}
