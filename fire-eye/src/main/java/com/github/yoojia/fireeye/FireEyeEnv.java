package com.github.yoojia.fireeye;

import android.util.Log;

/**
 * FireEye Env
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class FireEyeEnv {

    /**
     * 是否开启调试信息输出
     */
    private static boolean isDebug = false;

    /**
     * 是否输出更多详细的调试信息
     */
    private static boolean isVerbose = false;

    /**
     * 设置是否开启调试功能
     */
    public static void setDebug(boolean isDebug){
        FireEyeEnv.isDebug = isDebug;
    }

    /**
     * 设置是否开启详情输出功能
     */
    public static void setVerbose(boolean isVerbose){
        FireEyeEnv.isVerbose = isVerbose;
    }

    /**
     * 可控的调试信息输出接口
     * @param tag Tag
     * @param message 消息内容
     */
    public static void log(String tag, String message){
        if (isDebug) Log.d(tag, message);
    }

    public static void verbose(String tag, String message){
        if (isDebug && isVerbose) Log.d(tag, message);
    }
}
