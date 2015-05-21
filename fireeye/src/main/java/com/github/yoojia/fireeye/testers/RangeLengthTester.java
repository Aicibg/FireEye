package com.github.yoojia.fireeye.testers;

/**
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class RangeLengthTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final long minLength = minIntValue;
        final long maxLength = maxIntValue;
        final long length = content.length();
        return minLength <= length && length <= maxLength;
    }
}
