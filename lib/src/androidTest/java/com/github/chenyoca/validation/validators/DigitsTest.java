package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class DigitsTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new DigitsValidator(Type.Digits, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "123",
                "123343434",
                ""+Integer.MAX_VALUE,
                "0123456789",
                "13800138000",
                "00000",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "aaa123",
                "###",
                "123343434L",
                "123E43F",
                "1E222",
                "1.222",
                "abc",
                "/**//%%",
                "0x0123",
                "0xFFFF",
        };
    }
}
