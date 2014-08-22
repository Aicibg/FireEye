package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EqualsToLongTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        EqualsToValidator v = new EqualsToValidator(Type.EqualsTo, null);
        v.debug(true);
        return v;
    }

    @Override
    protected long[] setUpLongValues() {
        return new long[]{314159};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "314159",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "31415901",
                "3141590",
                "3141599",
                "3141599a",
                "3141599L",
        };
    }
}
