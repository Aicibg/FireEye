package com.github.yoojia.fireeye.testers;

/**
 * 最大长度
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class MaxLengthTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final long maxLength = intValue;
        return content.length() <= maxLength;
    }
}
