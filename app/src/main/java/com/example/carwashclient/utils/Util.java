package com.example.carwashclient.utils;

import android.content.res.Resources;

import com.example.carwashclient.gloab.MyApplication;

import static com.example.carwashclient.gloab.MyApplication.context;

/**
 * 作者：Created by chendeqiang on 2017/8/15
 * 邮箱：keshuixiansheng@126.com
 * 描述：
 */
public class Util {

    //这个是在主线程去更新ui,在没有上下文的环境,
    public static void runOnUIThread(Runnable runnable) {
        MyApplication.mainHandler.post(runnable);
    }

    //得到字符串数组信息
    public static String[] getStringArray(int resId) {
        //getResources:R
        return getResources().getStringArray(resId);
    }
    //得到资源管理的类
    public static Resources getResources() {
        return context.getResources();
    }
}
