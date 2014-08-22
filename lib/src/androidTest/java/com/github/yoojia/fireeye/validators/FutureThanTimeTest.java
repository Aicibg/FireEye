package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-08-13
 */
public class FutureThanTimeTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsFuture, null);
    }

    @Override
    protected String[] setUpStringValues() {
        return new String[]{
                "HH:mm:ss",
                "11:06:01"
        };
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "11:06:02",
                "23:00:00",
                "18:56:54",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "11:06:00",
                "10:59:56",
                "00:00:00",
                "2015-01-01 00:00:00",
                "2099-01-01 00:00:00",
        };
    }
}
