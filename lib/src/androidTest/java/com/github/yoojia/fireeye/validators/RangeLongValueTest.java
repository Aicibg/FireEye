package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class RangeLongValueTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new RangeValueValidator(Type.RangeValue, null);
    }

    @Override
    protected long[] setUpLongValues() {
        return new long[]{5,10};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "5",
                "5.0",
                "6",
                "9",
                "9.99999",
                "10",
                "10.0",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "-1",
                "0",
                "4",
                "10.00001",
                "11",
                "11",
        };
    }
}
