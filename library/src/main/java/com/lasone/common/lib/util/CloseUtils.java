package com.lasone.common.lib.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * io 关闭相关的辅助类
 */
public final class CloseUtils {

    private CloseUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("can't be instantiated");
    }

    /**
     * 关闭 IO 流
     */
    public static void closeIO(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void closeIOQuietly(final Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
