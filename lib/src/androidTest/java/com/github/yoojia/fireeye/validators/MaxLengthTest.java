package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class MaxLengthTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new MaxLengthValidator(Type.MaxLength, null);
    }

    @Override
    protected long[] setUpLongValues() {
        return new long[]{20};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "",
                "1",
                "1234567890123456789",
                "12345678901234567890"
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "123456789012345678901",
                "1234567890123456789012",
        };
    }
}
