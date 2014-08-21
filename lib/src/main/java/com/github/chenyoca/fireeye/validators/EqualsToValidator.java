package com.github.chenyoca.fireeye.validators;

import android.util.Log;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

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
        try{
            switch (extraType){
                case Long:
                    final long inputLong = Long.parseLong(inputValue);
                    return inputLong == extraLong[0];
                case Double:
                    final double inputDouble = Double.parseDouble(inputValue);
                    return inputDouble == extraFloat[0];
                case String:
                    return inputValue.equals(extraString[0]);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
            if (debug){
                System.out.println("[>] Number format error ! InputValue: " + inputValue);
            }
            this.error = e.getMessage();
        }
        return false;
    }

}
