package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EqualsToTest extends GroupTester {

    @Override
    protected AbstractValidator validator() {
        return new EqualsToValidator(Type.EqualsTo, null);
    }

    @Override
    protected String[] stringValues() {
        return new String[]{"chenyoca"};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "chenyoca"
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "Yoojia.chen"
        };
    }
}
