package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Value in max
 */
class MaxValueValidator extends ValueAbstractValidator {

    public MaxValueValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    protected boolean withExtraLong(double inputValue) {
        return inputValue <= extraLong[0];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return inputValue <= extraFloat[0];
    }
}
