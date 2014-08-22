package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class IPTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new IPv4Validator(Type.IPv4, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "0.0.0.0",
                "0.0.0.1",
                "127.0.0.1",
                "127.0.0.0",
                "127.0.0.255",

                "255.255.255.0",
                "255.255.255.255",

                "192.168.0.1",
                "192.168.0.199",
                "192.168.0.255",

                "199.154.37.214",
                "97.67.44.20",
                "97.67.44.255",
                "254.67.44.255",

                "1.67.44.255",
                "1.2.3.4",
                "8.8.8.8",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a.b.e.d",
                "1.2.3.4.5",
                "256.256.256.256",
                "192.168.1.256",
                "192.168.1.256",
        };
    }
}
