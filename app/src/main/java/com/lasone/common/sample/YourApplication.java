package com.lasone.common.sample;

import android.app.Application;

import com.lasone.common.lib.Utils;

/**
 * @author AsOne.L
 */
public class YourApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
