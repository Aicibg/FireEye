package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class MinLengthTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new MinLengthValidator(Type.MinLength, null);
    }

    @Override
    protected long[] setUpLongValues() {
        return new long[]{5};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "12345",
                "123456",
                "123457777777",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "",
                "1",
                "1234",
        };
    }
}
