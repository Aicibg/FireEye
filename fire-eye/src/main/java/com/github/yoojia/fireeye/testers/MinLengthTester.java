package com.github.yoojia.fireeye.testers;

/**
 * 最小长度
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class MinLengthTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final long minLength = intValue;
        return content.length() >= minLength;
    }
}
