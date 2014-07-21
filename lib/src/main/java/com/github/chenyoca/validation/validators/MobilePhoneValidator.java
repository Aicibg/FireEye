package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Chinese mobile phone
 */
class MobilePhoneValidator extends AbstractValidator {

    static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";

    public MobilePhoneValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return isMatched(PHONE_REGEX, inputValue);
    }

}
