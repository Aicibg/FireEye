package com.github.yoojia.fireeye.testers;

/**
 * 内容或者数值不相同
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-06-05
 * @since   2.1
 */
public class NotEqualsToTester extends AbstractValuesTester {
    @Override
    public boolean test(String content) {
        if (intValue != null){
            return intValue.longValue() != Long.valueOf(content);
        }else if (floatValue != null){
            return floatValue.doubleValue() != Double.valueOf(content);
        }else{
            return !stringValue.equals(content);
        }
    }
}
