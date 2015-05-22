package com.github.yoojia.fireeye;

import android.content.Context;

/**
 * 静态匹配模式
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-20
 * @since 2.0
 */
public enum StaticPattern {

    Required("此为必填项目"),
    NotBlank("不能是任何空值"),
    Digits("只能输入数字"),
    Email("邮箱地址错误"),
    Host("域名地址错误"),
    URL("网络地址错误"),
    IPv4("IP地址错误"),
    Numeric("只能输入数值"),
    BankCard("银行卡/信用卡号码错误"),
    IDCard("身份证号错误"),
    Mobile("手机号错误"),
    VehicleNumber("车牌号错误");

    private String mMessage;
    private int mMessageResId = -1;

    /**
     * 设置提示消息内容
     * @param message 消息内容
     */
    public StaticPattern setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 设置提示消息内容的资源ID
     * @param msgId 资源ID
     */
    public StaticPattern setMessage(int msgId){
        mMessageResId = msgId;
        return this;
    }

    private StaticPattern(String message){
        mMessage = message;
    }

    String getMessage() {
        return mMessage;
    }


    void tryMessageId(Context context){
        if (mMessageResId > 0){
            mMessage = context.getString(mMessageResId);
        }
    }
}
