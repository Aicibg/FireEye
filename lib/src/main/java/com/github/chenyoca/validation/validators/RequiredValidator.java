package com.github.chenyoca.validation.validators;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Required runner.
 */
class RequiredValidator extends AbstractValidator {

    public RequiredValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return ! TextUtils.isEmpty(inputValue);
    }
}
