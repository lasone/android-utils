package com.lasone.common.lib.util;


import android.app.Activity;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.lasone.common.lib.Utils;
import com.lasone.common.lib.WeakHandler;

/**
 * 防重复点击的toast
 *
 * @author AsOne.L
 */
public final class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("can't be instantiated");
    }

    public static void makeToast(@StringRes int resId) {
        makeToast(Utils.getContext().getString(resId));
    }


    public static void makeToast(String msg) {
        makeToast(msg, 2000);
    }

    public static void makeToast(@StringRes int resId, int duration) {
        makeToast(Utils.getContext().getString(resId), duration);
    }

    /**
     * 重复点击时，会直接替换文本，不会重新创建新的Toast并加入队列中一条条显示
     */
    public static void makeToast(String msg, int duration) {
        showToast(msg, duration);
    }

    /**
     * 可以在任意线程里面显示
     */
    public static void makeToast(final Activity activity, final String text) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            if (activity == null) {
                return;
            }
            showToast(text, 2000);
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(text, 2000);
                }
            });
        }
    }

    /**
     * 可以在任意线程里面显示
     */
    public static void makeToast(final Activity activity, final String text, final int duration) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            showToast(text, duration);
        } else {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(text, duration);
                }
            });
        }
    }

    private static Toast mToast;
    private static WeakHandler mHandler = new WeakHandler();
    private static Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    private static void showToast(String msg, int duration) {
        mHandler.removeCallbacks(mRunnable);
        if (mToast != null) {
            mToast.setText(msg);
            mToast.show();
        } else {
            mToast = Toast.makeText(Utils.getContext(), msg, Toast.LENGTH_SHORT);
            mToast.show();
        }
        mHandler.postDelayed(mRunnable, duration);
    }
}