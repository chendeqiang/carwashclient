package com.example.carwashclient.ui.widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.carwashclient.R;
import com.example.carwashclient.utils.Util;

import java.util.List;



public abstract class Loadpager extends FrameLayout {

    private View mLoadView;
    private View mErrorView;
    private View mSuccessView;

    public Loadpager(Context context) {
        this(context, null);
    }

    public Loadpager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Loadpager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化
        init();
    }

    //谁用谁传
    private void init() {
        //加载中界面
        if (mLoadView == null) {
            mLoadView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        if (mErrorView == null) {
            mErrorView = View.inflate(getContext(), R.layout.page_error, null);
            RelativeLayout reloadView = (RelativeLayout) mErrorView.findViewById(R.id.rl_root);
            reloadView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //重新请求数据
                    mLoadState = LOADSTATE.LOADING;
                    changPage();
                    errorLoad(500);
                }
            });

        }
        if (mSuccessView == null) {
            mSuccessView = createSuccessView();
            if (mSuccessView == null) {
                throw new RuntimeException("你真小白,来个布局!");
            }

        }

        //添加界面
        addView(mLoadView);
        addView(mErrorView);
        addView(mSuccessView);

        //切换ui
        changPage();

        //自动切换ui
        showPager();


    }

    private void errorLoad(int time) {
        requestData(time);
    }


    //根据网络数据自动切换ui
    public void showPager() {
        requestData(0);
    }

    public void requestData(final int time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(time);
                //得到对象
          /*     Object object =  getNetData();
                //检测数据
                mLoadState = checkData(object);*/
                mLoadState = checkData(getNetData());

                //主线程切换界面
                Util.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        //切换界面
                        changPage();
                    }
                });

            }
        }).start();
    }

    //返回当前的状态
    private LOADSTATE checkData(Object object) {
        /**
         * 1. 判断当前的数据
         * 2. 如果是空,返回错误的状态
         * 3. 如果不为空,判断是否是对象
         * 4. 如果是集合,那么判断个数是否大于0
         */
        if (object == null) {
            return LOADSTATE.ERROR;
        } else {
            if (object instanceof List) {
                //是集合
                List list = (List) object;
                if (list.size() > 0) {
                    //大于0说明有数据
                    return LOADSTATE.SUCCESS;
                } else {
                    //没有数据
                    return LOADSTATE.ERROR;
                }
            } else {
                //不是集合
                return LOADSTATE.SUCCESS;
            }
        }


    }

    //获取数据
    //但是要考虑对象有一个对象跟集合
    //谁用谁传
    public abstract Object getNetData();

    //根据当前的状态去切换页面
    private void changPage() {
        mLoadView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mSuccessView.setVisibility(View.GONE);

        //alt+J--->选中相同的代码
        switch (mLoadState) {
            case LOADING:

                mLoadView.setVisibility(View.VISIBLE);
                break;
            case ERROR:

                mErrorView.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:

                mSuccessView.setVisibility(View.VISIBLE);
                break;

            default:
                break;

        }

    }

    //当前的界面状态
    public enum LOADSTATE {
        LOADING,//加载中
        ERROR,//错误
        SUCCESS//成功
    }

    //当前的状态
    private LOADSTATE mLoadState = LOADSTATE.LOADING;//加载中状态

    //成功的界面
    public abstract View createSuccessView();
}
