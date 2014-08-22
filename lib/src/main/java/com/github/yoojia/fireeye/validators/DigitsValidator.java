package com.github.yoojia.fireeye.validators;

import android.text.TextUtils;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

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
