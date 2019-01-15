package com.lasone.common.lib.util;

import android.app.Activity;

import java.util.Stack;


/**
 * Activity 堆栈管理类
 *
 * @author AsOne.L
 */
public class ActivityStackManager {

    private static volatile ActivityStackManager sInstance;
    //接收activity的Stack
    private static Stack<Activity> mActivityStack;

    private ActivityStackManager() {
        mActivityStack = new Stack<>();
    }

    public static ActivityStackManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityStackManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityStackManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加 activity 到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        if (activity != null) {
            mActivityStack.add(activity);
            LogUtils.d(activity.getClass().getSimpleName() + mActivityStack.size());
        }
    }

    /**
     * 将 activity 移出栈
     */
    public void removeActivity(Activity activity) {
        if (mActivityStack != null && activity != null) {
            mActivityStack.remove(activity);
            LogUtils.d(activity.getClass().getSimpleName() + mActivityStack.size());
        }
    }

    /**
     * 获得当前的 activity (即最上层)
     */
    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (mActivityStack.size() > 0) {
//            currentActivity = mActivityStack.get(mActivityStack.size() - 1);
            currentActivity = mActivityStack.lastElement();
        }

        /*Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
            if(currentActivity ==null){
                if(mActivityStack.size()>0){
                    currentActivity = mActivityStack.get(mActivityStack.size()-1);
                }
            }
            if(currentActivity !=null && currentActivity.isFinishing()){
                currentActivity = null;
            }
        }else {
            if(mActivityStack.size()>0){
                currentActivity = mActivityStack.get(mActivityStack.size()-1);
            }
            if(currentActivity !=null && currentActivity.isFinishing()){
                currentActivity = null;
            }
        }*/
        return currentActivity;
    }

    /**
     * activity 是否在栈顶
     */
    public boolean isTopActivity(Class<? extends Activity> activityExpected) {
        Activity activity = getCurrentActivity();
        return activity != null && activity.getClass().equals(activityExpected);
    }

    /**
     * activity 是否在栈顶
     */
    public boolean isTopActivity(Activity activity) {
        return getCurrentActivity().equals(activity);
    }

    /**
     * 查找栈中是否存在指定的 activity
     */
    public boolean havaActivity(Class<? extends Activity> cls) {
        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取栈中指定的 activity
     */
    public Activity getActivity(Class<? extends Activity> activityExpected) {
        if (mActivityStack == null) {
            return null;
        }

        for (Activity act : mActivityStack) {
            if (act.getClass().equals(activityExpected)) {
                return act;
            }
        }
        return null;
    }

    /**
     * 判断某个activity是否还存活
     */
    public boolean isActivityAlive(Class<? extends Activity> cls) {
        if (mActivityStack == null) {
            return false;
        }

        for (Activity act : mActivityStack) {
            if (act.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束指定 activity
     */
    public void finishActivity(Activity activity) {
        if (mActivityStack == null) {
            return;
        }

        if (getActivity(activity.getClass()) != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack == null) {
            return;
        }

        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束除 cls 外的所有 activity
     */
    public void finishAllActivityExceptOne(Class<? extends Activity> cls) {
        while (!mActivityStack.empty()) {
            Activity activity = getCurrentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            finishActivity(activity);
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        while (!mActivityStack.empty()) {
            Activity activity = getCurrentActivity();
            finishActivity(activity);
        }
    }
}
