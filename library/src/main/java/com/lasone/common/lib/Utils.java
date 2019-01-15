package com.lasone.common.lib;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.lasone.common.lib.util.ActivityStackManager;
import com.lasone.common.lib.util.LogUtils;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author AsOne.L
 */
public class Utils {

    private static Application mApplication;
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    //保存当前 activity
    private static WeakReference<Activity> sCurrentActivityWeakRef;
    private static boolean isCurrentRunningForeground = true;
    private static boolean mDebugMode = true;

    private Utils() {
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * Init utils
     */
    public static void init(final Context context) {
        init((Application) context.getApplicationContext());
    }

    /**
     * Init utils
     */
    public static void init(final Application app) {
        mApplication = app;
        mApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
    }

    /**
     * 获取应用程序的 Application
     */
    public static Application getApp() {
        if (mApplication != null) return mApplication;
        throw new NullPointerException("u should init Utils first");
    }

    /**
     * 获取应用程序的 Context
     */
    public static Context getContext() {
        if (mApplication != null) return mApplication.getApplicationContext();
        throw new NullPointerException("u should init Utils first");
    }

    private static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ActivityStackManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity ac) {
            if (!isCurrentRunningForeground) {
                isCurrentRunningForeground = isRunningForeground();
            }
            setCurrentActivity(ac);
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            isCurrentRunningForeground = isRunningForeground();
            if (!isCurrentRunningForeground) {
                LogUtils.e("切到后台");
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ActivityStackManager.getInstance().finishActivity(activity);
        }
    }

    /**
     * 判断是否在后台运行
     */
    public static boolean isRunningForeground() {
        ActivityManager activityManager = (ActivityManager) mApplication.getSystemService(Context.ACTIVITY_SERVICE);
        assert activityManager != null;
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        // 枚举进程
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(mApplication.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取当前 activity
     */
    public static Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<Activity>(activity);
    }

    public static void setDebugMode(boolean debugMode) {
        mDebugMode = debugMode;
    }

    public static boolean isDebug() {
        return mApplication == null || mDebugMode;
    }
}
