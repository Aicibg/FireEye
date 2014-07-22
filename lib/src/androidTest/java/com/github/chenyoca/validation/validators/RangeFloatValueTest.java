package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class RangeFloatValueTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new RangeValueValidator(Type.RangeValue, null);
    }

    @Override
    protected double[] setUpDoubleValues() {
        return new double[]{5.5,10.5};
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "5.50001",
                "5.6",
                "6",
                "9",
                "9.99999",
                "10",
                "10.0",
                "10.5",
                "10.50",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "-1",
                "0",
                "4",
                "5.1",
                "5.49999",
                "5.00001",
                "10.00001",
                "10.501",
                "10.5001",
                "11",
                "11",
        };
    }
}
