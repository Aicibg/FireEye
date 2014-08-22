package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-08-13
 */
public class FutureThanDateTimeTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsFuture, null);
    }

    @Override
    protected String[] setUpStringValues() {
        return new String[]{
                "yyyy-MM-dd HH:mm:ss",
                "2014-08-13 00:00:00"
        };
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "2088-01-01 00:00:00",
                "2015-07-20 21:00:59",
                "2014-08-13 00:00:01",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "2000-01-01 00:00:00",
                "2014-07-20 21:00:00",
                "2014-08-12 23:59:59",
        };
    }
}
