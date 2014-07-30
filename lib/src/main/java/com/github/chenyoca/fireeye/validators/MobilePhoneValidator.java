package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

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
