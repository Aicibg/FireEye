package com.github.yoojia.fireeye.testers;

import android.util.Log;

import com.github.yoojia.fireeye.FireEyeEnv;

import java.util.regex.Pattern;

/**
 * 校验器
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public abstract class AbstractTester {

    /**
     * 发生异常时产生的消息
     */
    private String mExceptionMessage;

    public String getExceptionMessage() {
        return mExceptionMessage;
    }

    /**
     * 校验输入内容。如果校验通过返回True，否则返回False。
     * @param content 校验内容
     * @return 如果校验通过返回True，否则返回False。
     */
    public final boolean performTest(String content){
        if (FireEyeEnv.isDebug) {
            Log.d("Tester", "Tester > " + this.getClass().getSimpleName());
        }
        try{
            mExceptionMessage = null;
            return test(content);
        }catch (Exception e){
            mExceptionMessage = e.getMessage();
            return false;
        }
    }

    /**
     * 校验器实现类实现真正的校验方法
     * @param content 内容
     * @return 是否校验通过
     */
    protected abstract boolean test(String content);

    /**
     * 校验内容是否匹配指定正则表达式
     * @param regex 正则表达式
     * @param inputValue 内容
     * @return 是否匹配
     */
    protected static boolean testRegex(String regex, CharSequence inputValue){
        return Pattern.compile(regex).matcher(inputValue).matches();
    }
}
