package com.seven.gengbaolong.sevenmeishi.utils;

import java.sql.Timestamp;

/**
 * Created by gengbaolong on 2017/3/8.
 */

public class TimeUtils {
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }


    public static String getTimestamp() {
        return new Timestamp(getCurrentTime()).toString();
    }
}
