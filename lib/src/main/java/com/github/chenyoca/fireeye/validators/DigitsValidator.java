package com.github.chenyoca.fireeye.validators;

import android.text.TextUtils;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Only digits
 */
class DigitsValidator extends AbstractValidator {

    public DigitsValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
