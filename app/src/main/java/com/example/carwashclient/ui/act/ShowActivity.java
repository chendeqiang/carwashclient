package com.example.carwashclient.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carwashclient.R;
import com.example.carwashclient.ui.fragment.BaseFragment;
import com.example.carwashclient.ui.fragment.OrderFragment;
import com.example.carwashclient.utils.Constants;
import com.example.carwashclient.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {
    private static final String TAG = "ShowActivity";
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @BindView(R.id.toolbarTitle)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fl_show_layout)
    FrameLayout mFlShowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Log.d(TAG, "onCreate: ShowActivity");

        //取数据
        Class<BaseFragment> clazz = (Class<BaseFragment>) extras.getSerializable(Constants.ShowActivity.CLASS_NAME);
        Bundle bundleExtra = extras.getBundle(Constants.ShowActivity.BUNDLE);
        String title = extras.getString(Constants.ShowActivity.TITLE);
        boolean isShowBack = extras.getBoolean(Constants.ShowActivity.SHOW_BACK);
        boolean isShowSearch = extras.getBoolean(Constants.ShowActivity.SHOW_SEARCH);

        if (isShowBack) {
            mIvBack.setVisibility(View.VISIBLE);
            mIvBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            mIvBack.setVisibility(View.INVISIBLE);
        }

        if (isShowSearch) {
            mIvSearch.setVisibility(View.VISIBLE);
            mIvSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast("search");
                }
            });
        } else {
            mIvSearch.setVisibility(View.INVISIBLE);
        }

        //显示fragment
        try {
            BaseFragment baseFragment = clazz.newInstance();
            baseFragment.setArguments(bundleExtra);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_show_layout, baseFragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化ToolBar
        if (!TextUtils.isEmpty(title)) {
            initToolBar(title);
        } else {
            //隐藏标题栏
            //            Utils.hideTitle(this);
            mToolbar.setVisibility(View.GONE);
        }
    }

    private void initToolBar(String title) {
        mToolbar.setTitle("");
        mToolbarTitle.setText(title);
        setSupportActionBar(mToolbar);
    }
}
