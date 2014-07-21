package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Host
 */
class HostValidator extends AbstractValidator {

    static final String HOST_REGEX = "^([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,65}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";

    public HostValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return isMatched(IPv4Validator.IPV4_REGEX, inputValue) || isMatched(HOST_REGEX, inputValue);
    }

}
