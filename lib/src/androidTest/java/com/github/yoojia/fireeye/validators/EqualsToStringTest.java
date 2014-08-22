package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EqualsToStringTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        EqualsToValidator v = new EqualsToValidator(Type.EqualsTo, null);
        v.debug(true);
        return v;
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
