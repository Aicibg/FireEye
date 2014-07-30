package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * IPv4
 */
class IPv4Validator extends AbstractValidator {

    static final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public IPv4Validator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return isMatched(IPV4_REGEX, inputValue);
    }

}
