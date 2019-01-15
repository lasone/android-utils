package com.lasone.common.lib.helper;

import android.text.InputFilter;
import android.text.Spanned;


/**
 * 限定 editText 输入的最小值与最大值的过滤器
 *
 * @author AsOne.L
 * eg   et.setFilters(new InputFilter[]{new InputFilterMinMaxHelper("0", "10000000")});
 */
public class InputFilterMinMaxHelper implements InputFilter {

    private double min, max;

    public InputFilterMinMaxHelper(String min, String max) {
        this.min = Double.parseDouble(min);
        this.max = Double.parseDouble(max);
    }


    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (Exception ignored) {
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}