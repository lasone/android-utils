package com.lasone.common.lib.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.lasone.common.lib.Utils;

/**
 * 屏幕相关
 *
 * @author AsOne.L
 */
public final class ScreenUtils {

    private ScreenUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获得屏幕高度，返回像素
     *
     * @return 屏幕高度 单位: px
     */
    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.widthPixels;
    }

    /**
     * 获得屏幕宽度,返回像素
     *
     * @return 屏幕宽度 单位: px
     */
    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        if (windowManager != null && windowManager.getDefaultDisplay() != null) {
            windowManager.getDefaultDisplay().getMetrics(dm);
        }
        return dm.heightPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @return 状态栏的高度
     */
    public static int getStatusHeight() {
        int statusBarHeight = 0;
        Resources resources = Utils.getApp().getResources();
        int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelOffset(resId);
        }
        return statusBarHeight;
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;

    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth();
        int height = getScreenHeight();
        Bitmap bp;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取 显示信息
     */
    public static DisplayMetrics getDisplayMetrics() {
        return Utils.getContext().getResources().getDisplayMetrics();
    }

    /**
     * 打印屏幕信息
     */
    public static void printDisplayInfo() {
        DisplayMetrics dm = Utils.getContext().getResources().getDisplayMetrics();
        String displayInfo = " 屏幕信息:  " +
                "\ndensity         :" + dm.density +
                "\ndensityDpi      :" + dm.densityDpi +
                "\nheightPixels    :" + dm.heightPixels +
                "\nwidthPixels     :" + dm.widthPixels +
                "\nscaledDensity   :" + dm.scaledDensity +
                "\nxdpi            :" + dm.xdpi +
                "\nydpi            :" + dm.ydpi;
        LogUtils.i(displayInfo);
    }

}
