package com.lasone.common.lib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.lasone.common.lib.Utils;

/**
 * @author AsOne.L
 */
public class SharedPreferencesUtils {

    private static volatile SharedPreferencesUtils sInstance;
    private SharedPreferences mPreferences;

    private SharedPreferencesUtils(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            mPreferences = Utils.getContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        } else {
            mPreferences = PreferenceManager.getDefaultSharedPreferences(Utils.getContext());
        }
    }

    public static SharedPreferencesUtils getInstance(String fileName) {
        if (sInstance == null) {
            synchronized (SharedPreferencesUtils.class) {
                if (sInstance == null) {
                    sInstance = new SharedPreferencesUtils(fileName);
                }
            }
        }
        return sInstance;
    }

    public void putBoolean(String key, boolean value) {
        if (getEditor() != null) {
            getEditor().putBoolean(key, value).apply();
        }

    }

    public boolean getBoolean(String key, boolean defaultValue) {
        if (mPreferences != null && mPreferences.contains(key)) {
            return mPreferences.getBoolean(key, defaultValue);
        }
        return defaultValue;
    }

    public void putFloat(String key, float value) {
        if (getEditor() != null) {
            getEditor().putFloat(key, value).apply();
        }
    }

    public float getFloat(String key, float defaultValue) {
        if (mPreferences != null && mPreferences.contains(key)) {
            return mPreferences.getFloat(key, defaultValue);
        }
        return defaultValue;
    }

    public void putInt(String key, int value) {
        if (getEditor() != null) {
            getEditor().putInt(key, value).apply();
        }
    }

    public int getInt(String key, int defaultValue) {
        if (mPreferences != null && mPreferences.contains(key)) {
            return mPreferences.getInt(key, defaultValue);
        }
        return defaultValue;
    }

    public void putLong(String key, long value) {
        if (getEditor() != null) {
            getEditor().putLong(key, value).apply();
        }
    }

    public long getLong(String key, long defaultValue) {
        if (mPreferences != null && mPreferences.contains(key)) {
            return mPreferences.getLong(key, defaultValue);
        }
        return defaultValue;
    }

    public void putString(String key, String value) {
        if (getEditor() != null) {
            getEditor().putString(key, value).apply();
        }
    }

    public String getString(String key, String defaultValue) {
        if (mPreferences != null && mPreferences.contains(key)) {
            return mPreferences.getString(key, defaultValue);
        }
        return defaultValue;
    }


    public void removeByKey(String key) {
        if (mPreferences != null && mPreferences.contains(key)) {
            getEditor().remove(key).apply();
        }
    }

    public void clear() {
        if (getEditor() != null) {
            getEditor().clear().apply();
        }
    }

    private SharedPreferences.Editor getEditor() {
        return mPreferences.edit();
    }

}
