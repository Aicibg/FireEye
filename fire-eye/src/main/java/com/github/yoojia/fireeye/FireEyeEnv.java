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
    public static boolean isDebug = false;

    /**
     * 可控的调试信息输出接口
     * @param tag Tag
     * @param message 消息内容
     */
    public static void log(String tag, String message){
        if (isDebug) Log.d(tag, message);
    }
}
