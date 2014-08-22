package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EqualsToFloatTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        EqualsToValidator v = new EqualsToValidator(Type.EqualsTo, null);
        v.debug(true);
        return v;
    }

    @Override
    protected double[] setUpDoubleValues() {
        return new double[]{3.14159};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "3.14159",
                "3.141590",
                "3.141590",
                "3.1415900",
                "3.1415900",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "3.1415901f",
                "3.1415901",
                "3.14159001",
        };
    }
}
