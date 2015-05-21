package com.github.yoojia.fireeye.testers;

/**
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class MaxValueTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        final double maxValue = floatValue;
        return Double.valueOf(content) <= maxValue;
    }

}
