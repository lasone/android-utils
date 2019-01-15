package com.lasone.common.lib.util;

import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.Log;

import com.lasone.common.lib.BuildConfig;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * log 工具类
 *
 * @author AsOne.L
 */
public final class LogUtils {

    private static final String NULL_TAG = null;
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static final String NULL_TIPS = "Log with null object";

    private static final int LOG_MAX_LENGTH = 3000;
    private static final int STACK_TRACE_INDEX_4 = 4;
    private static final int STACK_TRACE_INDEX_5 = 5;

    private static final int Verbose = Log.VERBOSE;
    private static final int Debug = Log.DEBUG;
    private static final int Info = Log.INFO;
    private static final int Warm = Log.WARN;
    private static final int Error = Log.ERROR;
    private static final int Assert = Log.ASSERT;

    @IntDef({Verbose, Debug, Info, Warm, Error, Assert})
    @Retention(RetentionPolicy.SOURCE)
    @interface LogTypeMode {
    }

    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void v(String msg) {
        writeLog(Verbose, NULL_TAG, msg);
    }

    public static void v(String tag, Object... msgs) {
        writeLog(Verbose, tag, msgs);
    }

    public static void d(String msg) {
        writeLog(Debug, NULL_TAG, msg);
    }

    public static void d(String tag, Object... msgs) {
        writeLog(Debug, tag, msgs);
    }

    public static void i(String msg) {
        writeLog(Info, NULL_TAG, msg);
    }

    public static void i(String tag, Object... msgs) {
        writeLog(Info, tag, msgs);
    }

    public static void w(String msg) {
        writeLog(Warm, NULL_TAG, msg);
    }

    public static void w(String tag, Object... msgs) {
        writeLog(Warm, tag, msgs);
    }

    public static void e(String msg) {
        writeLog(Error, NULL_TAG, msg);
    }

    public static void e(String tag, Object... msgs) {
        writeLog(Error, tag, msgs);
    }

    public static void wtf(String msg) {
        writeLog(Assert, NULL_TAG, msg);
    }

    public static void wtf(String tag, Object... msgs) {
        writeLog(Assert, tag, msgs);
    }

    private static void writeLog(@LogTypeMode int type, String tagStr, Object... objects) {
        if (!BuildConfig.LOG_DEBUG) {
            return;
        }
        String[] contents = wrapperLogContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        printLog(type, tag, headString + msg);
    }

    /**
     * 打印 log（判断是否大于限制的字数）
     */
    private static void printLog(@LogTypeMode int type, String tag, String msg) {
        int index = 0;
        int length = msg.length();
        int countOfSub = length / LOG_MAX_LENGTH;

        if (countOfSub > 0) {
            for (int i = 0; i < countOfSub; i++) {
                String sub = msg.substring(index, index + LOG_MAX_LENGTH);
                printSub(type, tag, sub);
                index += LOG_MAX_LENGTH;
            }
            printSub(type, tag, msg.substring(index, length));
        } else {
            printSub(type, tag, msg);
        }
    }

    private static void printSub(@LogTypeMode int logType, String tag, String sub) {
        switch (logType) {
            case Verbose:
                Log.v(tag, sub);
                break;
            case Debug:
                Log.d(tag, sub);
                break;
            case Info:
                Log.i(tag, sub);
                break;
            case Warm:
                Log.w(tag, sub);
                break;
            case Error:
                Log.e(tag, sub);
                break;
            case Assert:
                Log.wtf(tag, sub);
                break;
        }
    }


    /**
     * 装饰 log
     */
    private static String[] wrapperLogContent(int stackTraceIndex, String tagStr, Object... objects) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[stackTraceIndex];

        String fileName = targetElement.getFileName();
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String tag = tagStr;
        if (TextUtils.isEmpty(tag)) {
            String className = targetElement.getClassName();
            String[] classNameInfo = className.split("\\.");
            if (classNameInfo.length > 0) {
                tag = classNameInfo[classNameInfo.length - 1];
            }
            if (className.contains("$")) {
                tag = className.split("\\$")[0];
            }
        }
        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[(" + fileName + ":" + lineNumber + ")#" + methodName + " ] ";
        return new String[]{tag, msg, headString};
    }

    /**
     * 获取 log 信息
     */
    private static String getObjectsString(Object... objects) {
        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

    /**
     * log 跟踪
     */
    public static void trace() {
        printStackTrace();
    }

    private static void printStackTrace() {
        if (!BuildConfig.LOG_DEBUG) {
            return;
        }
        Throwable tr = new Throwable();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        String message = sw.toString();
        String traceString[] = message.split("\\n\\t");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String trace : traceString) {
            sb.append(trace).append("\n");
        }
        String[] contents = wrapperLogContent(STACK_TRACE_INDEX_4, null, sb.toString());
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        printLog(Debug, tag, headString + msg);
    }

}
