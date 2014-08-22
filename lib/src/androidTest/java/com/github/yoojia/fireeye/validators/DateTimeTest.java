package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class DateTimeTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsDateTime, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "2010-01-01 00:00:00",
                "2014-1-1 0:0:0",
                "2014-1-1 1:1:0",
                "2014-1-1 1:1:0",
                "2014-1-1 1:1:1",
                "2014-12-12 12:12:12",
                "2014-12-12 23:59:59",
                "2014-12-13 00:00:00",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "yyyy-MM-dd HH:mm:ss",
                "2014-07-20+21:00:00",
                "2014-07-20 25:00:00",
                "2014-07-20 24:60:00",
                "2014-07-20 24:59:60",
        };
    }
}
