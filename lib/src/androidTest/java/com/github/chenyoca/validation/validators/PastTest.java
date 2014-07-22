package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class PastTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new DateTimeValidator(Type.IsPast, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
            "2011-07-20 21:00:00",
            "2011-07-20 21:00:00",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "2088-07-20 21:00:00",
        };
    }
}
