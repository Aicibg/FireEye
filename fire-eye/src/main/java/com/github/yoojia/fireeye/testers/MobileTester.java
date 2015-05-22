package com.github.yoojia.fireeye.testers;

/**
 * 手机号
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class MobileTester extends AbstractTester {

    static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";

    @Override
    public boolean test(String content) {
        return testRegex(PHONE_REGEX, content);
    }
}
