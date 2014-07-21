package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class FutureTest extends GroupTester {

    @Override
    protected AbstractValidator validator() {
        return new DateTimeValidator(Type.IsFuture, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "2088-01-01 00:00:00",
                "2015-07-20 21:00:59",
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
