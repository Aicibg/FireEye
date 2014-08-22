package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Length in min
 */
class MinLengthValidator extends AbstractValidator {

    public MinLengthValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return inputValue.length() >= extraLong[0];
    }

    @Override
    public void verifyValues() {
        checkRequiredLongValues();
    }
}
