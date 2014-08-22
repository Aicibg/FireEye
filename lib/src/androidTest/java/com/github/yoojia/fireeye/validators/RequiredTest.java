package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class RequiredTest extends GroupTester{
    @Override
    protected AbstractValidator setUpValidator() {
        return new RequiredValidator(Type.Required, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                " ",
                "   ",
                "a",
                "1",
                "123",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                ""
        };
    }
}
