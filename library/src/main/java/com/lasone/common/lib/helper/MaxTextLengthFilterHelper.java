package com.lasone.common.lib.helper;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * 限定 EditText 输入的字数的 Filter
 *
 * @author AsOne.L
 * eg   et.setFilters(new InputFilter[]{new MaxTextLengthFilterHelper(13)});
 */
public class MaxTextLengthFilterHelper implements InputFilter {

    private int mMaxLength;

    //构造方法中传入最多能输入的字数
    public MaxTextLengthFilterHelper(int max) {
        mMaxLength = max;
    }

    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if (keep <= 0) {
            return "";
        } else if (keep >= end - start) {
            return null;
        } else {
            return source.subSequence(start, start + keep);
        }
    }

}
