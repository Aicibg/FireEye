package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Credit card
 * !! Copyright -> http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js
 */
class CreditCardValidator extends AbstractValidator {

    public CreditCardValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        // accept only spaces, digits and dashes
        if ( ! isMatched("[\\d -]*", inputValue) ) {
            return false;
        }

        String value = inputValue.replaceAll("\\D", "");

        // Basing min and max length on
        // http://developer.ean.com/general_info/Valid_Credit_Card_Types
        int length = value.length();
        if ( length < 13 || length > 19 ) {
            return false;
        }

        char cDigit;
        int nCheck = 0, nDigit;
        boolean bEven = false;
        for ( int n = length - 1; n >= 0; n--) {
            cDigit = value.charAt(n);
            nDigit = Integer.parseInt(String.valueOf(cDigit), 10);
            if ( bEven ) {
                if ( (nDigit *= 2) > 9 ) {
                    nDigit -= 9;
                }
            }
            nCheck += nDigit;
            bEven = !bEven;
        }

        return (nCheck % 10) == 0;
    }

}
