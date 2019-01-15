package com.lasone.common.lib.util;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.lasone.common.lib.Utils;

/**
 * Alarm 相关的辅助类
 *
 * @author AsOne.L
 */
public class AlarmUtils {

    private AlarmUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 开启定时器
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void startAlarmIntent(int triggerAtMillis, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) Utils.getContext().getSystemService(Context.ALARM_SERVICE);
        if (manager != null) {
            manager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
        }
    }

    /**
     * 关闭定时器
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void stopAlarmIntent(PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) Utils.getContext().getSystemService(Context.ALARM_SERVICE);
        if (manager != null) {
            manager.cancel(pendingIntent);
        }
    }

    /**
     * 开启轮询服务
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void startAlarmService(Context context, int triggerAtMillis, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        startAlarmIntent(triggerAtMillis, pendingIntent);
    }

    /**
     * 停止轮询服务
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static void stopAlarmService(Context context, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        stopAlarmIntent(pendingIntent);
    }

}
