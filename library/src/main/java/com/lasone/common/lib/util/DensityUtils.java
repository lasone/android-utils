package com.lasone.common.lib.util;

import android.util.TypedValue;

import com.lasone.common.lib.Utils;

/**
 * 常用单位转换
 *
 * @author AsOne.L
 */
public final class DensityUtils {

    private DensityUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * px 转换为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * dp 转换为 px
     */
    public static int dp2px(float dpValue) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px 转换为 sp
     */
    public static int px2sp(float pxValue) {
        final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * sp 转换为 px
     */
    public static int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, Utils.getContext().getResources().getDisplayMetrics());
    }

}
