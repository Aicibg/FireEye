package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-16
 */
abstract class ValueAbstractValidator extends AbstractValidator {

    public ValueAbstractValidator(Type testType, String message) {
        super(testType, message);
    }

    @Override
    protected boolean isValid(String input) {
        double inputValue;
        try{
            inputValue = Double.valueOf(input);
        }catch (Exception e){
            message = e.getMessage();
            return false;
        }
        if (ExtraType.Long.equals(extraType)){
            return withExtraLong(inputValue);
        }else{
            return withExtraFloat(inputValue);
        }
    }

    @Override
    public void verifyValues() {
        checkRequiredLongFloatValues();
    }

    protected abstract boolean withExtraLong(double inputValue);
    protected abstract boolean withExtraFloat(double inputValue);
}
