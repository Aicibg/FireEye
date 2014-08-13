package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-08-13
 */
public class PastThanDateTimeTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsPast, null);
    }

    @Override
    protected String[] setUpStringValues() {
        return new String[]{
                "yyyy/MM/dd HH:mm:ss",
                "2014/08/13 00:00:00"
        };
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
            "2011/07/20 21:00:00",
            "2014/08/12 23:59:59",
            "2014/08/12 23:59:00",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "2088/07/20 21:00:00",
                "2014/08/13 00:00:01",
        };
    }
}
