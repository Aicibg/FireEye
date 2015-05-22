package com.github.yoojia.fireeye.testers;

/**
 * IP地址校验
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class IPv4Tester extends AbstractTester {

    static final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    @Override
    public boolean test(String content) {
        return testRegex(IPV4_REGEX, content);
    }
}
