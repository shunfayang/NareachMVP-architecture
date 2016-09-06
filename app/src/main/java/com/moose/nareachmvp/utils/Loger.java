package com.moose.nareachmvp.utils;

import android.util.Log;

/**
 * Created by Moose Yang on 2016/1/12.
 * todo Copy Right MooseStudio
 * 本类注释：
 */
public class Loger {

    private static String TAG = "moose";
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static final int ASSERT = 6;
    public static int level = DEBUG;

    public static void i(String msg) {
        if (level <= INFO)
            Log.d(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (level <= INFO)
            Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (level <= DEBUG)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG)
            Log.d(tag, msg);
    }

    public static void v(String msg) {
        if (level <= VERBOSE)
            Log.d(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (level <= VERBOSE)
            Log.d(tag, msg);
    }

}
