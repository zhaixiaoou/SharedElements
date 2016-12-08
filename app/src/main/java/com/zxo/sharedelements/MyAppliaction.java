package com.zxo.sharedelements;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Created by xiaoouzhai on 16/12/8.
 */


public class MyAppliaction extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("zxo")
            .methodCount(3)
            .hideThreadInfo()
            .logLevel(LogLevel.FULL)  //LogLevel.NONE 则不显示log,可以统一关闭
            .methodOffset(2);
    }
}
