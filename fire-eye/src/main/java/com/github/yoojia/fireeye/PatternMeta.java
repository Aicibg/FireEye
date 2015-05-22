package com.github.yoojia.fireeye;

import android.content.Context;

/**
 * 匹配模式配置对象
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-22
 * @since   2.3
 */
class PatternMeta<T> {

    final T pattern;
    final int messageId;

    String message;

    PatternMeta(T pattern, String message, int messageId) {
        this.pattern = pattern;
        this.message = message;
        this.messageId = messageId;
    }

    final void convertMessage(Context context){
        if (messageId > 0){
            message = context.getString(messageId);
        }
    }

}
