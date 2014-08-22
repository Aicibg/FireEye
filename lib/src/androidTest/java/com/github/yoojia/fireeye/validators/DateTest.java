package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class DateTest extends GroupTester {
    
    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsDate, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "2014-07-21",
                "2014-12-31",
                "2000-02-29",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "1",
                "1222",
                "abc",
                "yyyy-MM-dd",
                "2014-13-32",
                "2014-13-",
                "2014-02-30",
                "0000-02-30",
                "0000-00-00",
                "2013-02-29",
        };
    }
}
