package com.example.carwashclient.utils;

import com.example.carwashclient.gloab.MyApplication;

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

    //这是个子线程
    public static void runOnChildThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
