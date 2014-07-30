package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class HostTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new HostValidator(Type.Host, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "192.168.1.1",
                "225.20.1.1",
                "1.0.1.1",
                "www.baidu.com",
                "api.google.cn",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "aaa",
                "aaaa.gggg.gg.gg.",
                "300,200,199,29",
                "300.200.199.29",
                "256.200.199.29",
                "http://www.yoojia.cn",
        };
    }
}
