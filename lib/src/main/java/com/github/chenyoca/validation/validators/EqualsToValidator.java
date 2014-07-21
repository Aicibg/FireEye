package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Equal to a value
 */
class EqualsToValidator extends AbstractValidator {

    public EqualsToValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        String value = null;
        switch (extraType){
            case Long: value = String.valueOf(extraLong[0]); break;
            case Double: value = String.valueOf(extraFloat[0]); break;
            case String: value = extraString[0]; break;
        }
        return inputValue.equals(value);
    }

}
