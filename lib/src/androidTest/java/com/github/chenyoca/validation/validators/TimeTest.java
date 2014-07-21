package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class TimeTest extends GroupTester{

    @Override
    protected AbstractValidator validator() {
        return new DateTimeValidator(Type.IsTime, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "00:00",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "00",
                "2459",
                "245900",
                "hhmm",
                "hhmmss",
                "24:60",
                "25:59",
                "25:60",
                "25:60:",
                "24:59:60",
        };
    }
}
