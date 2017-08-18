package com.example.carwashclient.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.carwashclient.R;
import com.example.carwashclient.bean.MainTabs;
import com.example.carwashclient.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_main_show_layout)
    FrameLayout mFlMainShowLayout;
    @BindView(R.id.ftab_main_bottom_layout)
    FragmentTabHost mFtabMainBottomLayout;
    @BindView(R.id.iv_main_click)
    ImageView mIvMainClick;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mFtabMainBottomLayout.setup(this, getSupportFragmentManager(), R.id.fl_main_show_layout);
        MainTabs[] tabses = MainTabs.values();
        for (int i = 0; i < tabses.length; i++) {
            Bundle args = new Bundle();
            args.putString("point", i + "");
            addTab(tabses[i], args, i);
        }
        //去掉分隔线
        mFtabMainBottomLayout.getTabWidget().setDividerDrawable(null);
    }

    private void addTab(MainTabs tabse, Bundle args, int point) {
        TabHost.TabSpec tabspac = mFtabMainBottomLayout.newTabSpec(tabse.mTag);// 复用
        //设置显示的view
        View view = View.inflate(this, R.layout.tab_indicator, null);
        //动态的设置文字
        TextView title = (TextView) view.findViewById(R.id.tab_title);
        title.setText(tabse.mTitle);
        //动态的设置图片
        ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
        icon.setImageResource(tabse.mImage);
        //在第三个位置的时候不显示
        if (point == 2) {
            view.setVisibility(View.INVISIBLE);
        }
        tabspac.setIndicator(view);
        Class<?> clss = tabse.mClss;
        mFtabMainBottomLayout.addTab(tabspac, clss, args);
    }

    @OnClick(R.id.iv_main_click)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.slide_bottom_in, 0);
    }

    /**
     * 实现两秒内连续点击回退按钮退出效果
     */
    long latestTime;

    @Override
    public void onBackPressed() {
        //获取回退栈中Fragment的数量
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        //如果回退栈中还有1个Fragment说明已经是在主页了
        if (backStackEntryCount < 2) {
            if (System.currentTimeMillis() - latestTime < 2000) {
                finish();
                System.exit(0);
            } else {
                ToastUtil.showToast("再次点击退出");
                latestTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();//执行父类的方法，也是弹栈操作
    }
}
