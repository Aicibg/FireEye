package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Value in max
 */
class MinValueValidator extends ValueAbstractValidator {

    public MinValueValidator(Type testType, String message){
        super(testType,message);
    }

    @Override
    protected boolean withExtraLong(double inputValue) {
        return inputValue >= extraLong[0];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return inputValue >= extraFloat[0];
    }
}
