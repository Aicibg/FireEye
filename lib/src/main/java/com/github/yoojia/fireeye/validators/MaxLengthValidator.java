package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Length in max
 */
class MaxLengthValidator extends AbstractValidator {

    public MaxLengthValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return inputValue.length() <= extraLong[0];
    }

    @Override
    public void verifyValues() {
        checkRequiredLongValues();
    }
}
