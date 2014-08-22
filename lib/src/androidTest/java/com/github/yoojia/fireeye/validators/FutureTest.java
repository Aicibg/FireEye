package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class FutureTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsFuture, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "2088-01-01 00:00:00",
                "2017-07-20 21:00:59",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "2000-01-01 00:00:00",
                "2014-07-20 21:00:00",
        };
    }
}
