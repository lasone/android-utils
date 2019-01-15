package com.lasone.common.lib.util;

/**
 * @author AsOne.L
 */
public final class StringUtils {

    private static final String EMPTY = "";

    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * 判断字符串是否为 null 或长度为 0
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符串是否不为空且不为null
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是否为 null 或全为空格
     */
    public static boolean isTrimEmpty(final CharSequence cs) {
        return isBlank(cs);
    }

    /**
     * 判断char序列中否有字符为空或 null
     */
    public static boolean isAnyEmpty(final CharSequence... css) {
        if (css.length == 0) {
            return false;
        }
        for (final CharSequence cs : css) {
            if (isEmpty(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断所有的char序列是否有char 不为null 且不为空
     */
    public static boolean isNoneEmpty(final CharSequence... css) {
        return !isAnyEmpty(css);
    }

    /**
     * 判断是否所有的char序列都是空的(“”)或null
     */
    public static boolean isAllEmpty(final CharSequence... css) {
        if (css.length == 0) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (isNotEmpty(cs)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为 null 或全为空格
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为不为 null 且不是空格
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * 检查字符序列中是否有为空(“”)或仅为空格的
     */
    public static boolean isAnyBlank(final CharSequence... css) {
        if (css.length == 0) {
            return false;
        }
        for (final CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查所有字符序列是否为空(“”)、是否为空或仅为空白
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        return !isAnyBlank(css);
    }

    /**
     * 检查所有字符序列是否为空(“”)或空格
     **/
    public static boolean isAllBlank(final CharSequence... css) {
        if (css.length == 0) {
            return true;
        }
        for (final CharSequence cs : css) {
            if (isNotBlank(cs)) {
                return false;
            }
        }
        return true;
    }


    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * 判断两个字符串是否相等
     */
    public static boolean equals(final CharSequence s1, final CharSequence s2) {
        if (s1 == s2) return true;
        int length;
        if (s1 != null && s2 != null && (length = s1.length()) == s2.length()) {
            if (s1 instanceof String && s2 instanceof String) {
                return s1.equals(s2);
            } else {
                for (int i = 0; i < length; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

}
