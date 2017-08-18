package com.example.carwashclient.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwashclient.R;
import com.example.carwashclient.adapter.OrderListAdapter;
import com.example.carwashclient.bean.FragmentInfo;
import com.example.carwashclient.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Created by chendeqiang on 2017/8/16
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class OrderFragment extends BaseFragment {
    @BindView(R.id.tab_nav)
    TabLayout mTabNav;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.toolbarTitle)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private List<FragmentInfo> mShowItems = new ArrayList<>();

    @Override
    public Object getData() {
        return "";
    }

    @Override
    protected View createView() {
        View view = View.inflate(getContext(), R.layout.fragment_order_view_pager, null);
        ButterKnife.bind(this, view);
        mToolbar.setTitle("");
        mToolbarTitle.setText("订单列表");
        String[] titles = Util.getStringArray(R.array.order_tab_names);
        mShowItems.add(new FragmentInfo(new FinishOrderFragment(), titles[0]));
        mShowItems.add(new FragmentInfo(new UnFinishOrderFragment(), titles[1]));
        mViewPager.setAdapter(new OrderListAdapter(getFragmentManager(), mShowItems));
        mTabNav.setupWithViewPager(mViewPager);
        return view;
    }
}
