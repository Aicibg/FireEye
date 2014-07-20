package com.github.chenyoca.validation.validators;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

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
    public boolean test(String inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
