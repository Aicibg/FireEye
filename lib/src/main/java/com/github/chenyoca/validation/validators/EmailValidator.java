package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Email
 */
class EmailValidator extends AbstractValidator {

    static final String EMAIL_REGEX =
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public EmailValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return isMatched(EMAIL_REGEX, inputValue);
    }

}
