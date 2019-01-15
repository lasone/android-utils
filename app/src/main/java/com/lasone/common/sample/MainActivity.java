package com.lasone.common.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lasone.common.lib.util.LogUtils;
import com.lasone.common.lib.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtils.printDisplayInfo();
        LogUtils.e();
//        RandomUtils.
    }
}
