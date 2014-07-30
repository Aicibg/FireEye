package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EqualsToTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new EqualsToValidator(Type.EqualsTo, null);
    }

    @Override
    protected String[] setUpStringValues() {
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
