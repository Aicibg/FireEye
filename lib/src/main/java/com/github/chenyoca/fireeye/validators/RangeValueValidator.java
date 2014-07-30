package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Value in range
 */
class RangeValueValidator extends ValueAbstractValidator {

    public RangeValueValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    protected boolean withExtraLong(double inputValue) {
        return extraLong[0] <= inputValue && inputValue <= extraLong[1];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return extraFloat[0] <= inputValue && inputValue <= extraFloat[1];
    }
}
