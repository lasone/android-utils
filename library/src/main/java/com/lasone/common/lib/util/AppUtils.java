package com.lasone.common.lib.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

import com.lasone.common.lib.Utils;

/**
 * App 相关的辅助类
 *
 * @author AsOne.L
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取当前应用程序名称
     */
    public static String getAppName() {
        String versionName = "";
        try {
            PackageManager pm = Utils.getContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(Utils.getContext().getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("", "");
        }
        return versionName;
    }

    /**
     * 获取当前应用程序版本名
     */
    public static String getVersionName() {
        try {
            PackageManager packageManager = Utils.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    Utils.getContext().getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
