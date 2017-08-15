package com.example.carwashclient.gloab;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 作者：Created by chendeqiang on 2017/8/15
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class MyApplication extends Application {
    public static Context context;
    public static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取主线程的Handler
        mainHandler = new Handler();
        context = this;
    }
}
