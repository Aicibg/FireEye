package com.github.yoojia.fireeye.testers;

import android.text.TextUtils;

/**
 * 非空
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class RequiredValueTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        return ! TextUtils.isEmpty(content);
    }
}
