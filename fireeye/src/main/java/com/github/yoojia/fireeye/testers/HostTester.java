package com.github.yoojia.fireeye.testers;

/**
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class HostTester extends AbstractTester {

    static final String HOST_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

    @Override
    public boolean test(String content) {
        return testRegex(IPv4Tester.IPV4_REGEX, content) || testRegex(HOST_REGEX, content);
    }
}
