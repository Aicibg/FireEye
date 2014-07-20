package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Length in range
 */
class RangeLengthValidator extends AbstractValidator {

    public RangeLengthValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        int length = inputValue.length();
        return extraLong[0] <= length && length <= extraLong[1];
    }

    @Override
    public void onAdded() {
        checkRequiredLongValues();
    }
}
