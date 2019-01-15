package com.lasone.common.lib.util;

import java.util.Random;

/**
 * 随机工具类
 *
 * @author AsOne.L
 */

public class RandomUtils {

    private RandomUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取一个随机的 Boolean 值
     */
    public static boolean getRandom() {
        return new Random().nextBoolean();
    }

    /**
     * 获取[0,max]中的随机整数
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取[min,max] 中的随机整数
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        if (min == max) {
            return min;
        }

        return new Random().nextInt(max - min + 1) + min;
//        return (int) (Math.random() * (max - min + 1)) + min;
    }

    /**
     * 从字符串中中获取一个固定长度的随机字符串
     */
    public static String getRandom(String source, int length) {
        return source == null ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 从字符数组中获取一个固定长度的随机字符串
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }
        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 获取一个固定长度的随机数字字符串
     */
    public static String getRandomNumbers(int length) {
        /* 0-9 的 ASCII 码值[48,57] */
        String source = getAsciiCharacter(48, 57);
        return getRandom(source, length);
    }

    /**
     * 获取一个固定长度的随机字符串
     */
    public static String getRandomLetters(int length) {
        /* a-z 以及 A-Z*/
        String source = getAsciiCharacter(65, 90) + getAsciiCharacter(97, 122);
        return getRandom(source, length);
    }

    /**
     * 获取一个固定长度的随机大写字符串
     */
    public static String getRandomCapitalLetters(int length) {
        /* A-Z 的 ASCII 码值[65,90] */
        String source = getAsciiCharacter(65, 90);
        return getRandom(source, length);
    }

    /**
     * 获取一个固定长度的随机小写字符串
     */
    public static String getRandomLowerCaseLetters(int length) {
        /* a-z 的 ASCII 码值[97,122] */
        String source = getAsciiCharacter(97, 122);
        return getRandom(source, length);
    }

    /**
     * 获取一个固定长度的随机字符串，包含字符与数字
     */
    public static String getRandomNumbersAndLetters(int length) {
        String source = getAsciiCharacter(48, 57) +
                getAsciiCharacter(65, 90) +
                getAsciiCharacter(97, 122);
        return getRandom(source, length);
    }

    /**
     * 获取 Ascii 表中某一段的字符串
     */
    private static String getAsciiCharacter(int start, int end) {
        StringBuilder sb = new StringBuilder();
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        for (int i = start; i <= end; i++) {
            sb.append(String.valueOf((char) i));
        }
        return sb.toString();
    }


    /**
     * Shuffling algorithm, Randomly permutes the specified array using a default source of randomness
     */
    public static boolean shuffle(Object[] objArray) {
        if (objArray == null) {
            return false;
        }
        return shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array
     */
    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
            return false;
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return true;
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified int array using a default source of randomness
     */
    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }

        return shuffle(intArray, getRandom(intArray.length));
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified int array
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }
}
