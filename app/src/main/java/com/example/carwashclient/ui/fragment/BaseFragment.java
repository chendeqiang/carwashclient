package com.example.carwashclient.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carwashclient.ui.act.ShowActivity;
import com.example.carwashclient.ui.widget.Loadpager;
import com.example.carwashclient.utils.Constants;

public abstract class BaseFragment extends Fragment {

    private Loadpager mLoadpager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //得到数据的方法
        if (mLoadpager == null) {

            mLoadpager = new Loadpager(getContext()) {
                //得到数据的方法
                @Override
                public Object getNetData() {
                    return getData();
                }

                @Override
                public View createSuccessView() {
                    return createView();
                }
            };

        }
        return mLoadpager;
    }

    //返回一个数据
    public abstract Object getData();
    //返回一个界面
    protected abstract View createView();


    //刷新数据的方法
    public void refreshData() {
        mLoadpager.showPager();
    }

    //跳转fragment
    public void startFragment(Class<? extends BaseFragment> clss,Bundle bundle) {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.ShowActivity.CLASS_NAME, clss);
        intent.putExtra(Constants.ShowActivity.BUNDLE, bundle);
        startActivity(intent);
    }
    //跳转fragment
    public void startFragmentWithTitle(Class<? extends BaseFragment> clss,Bundle bundle,String title) {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.ShowActivity.CLASS_NAME, clss);
        intent.putExtra(Constants.ShowActivity.BUNDLE, bundle);
        intent.putExtra(Constants.ShowActivity.TITLE, title);
        startActivity(intent);
    }

}
