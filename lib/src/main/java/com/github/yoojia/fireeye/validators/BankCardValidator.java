package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * Credit card
 * !! Copyright -> http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js
 */
class BankCardValidator extends AbstractValidator {

    public BankCardValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        // accept only spaces, digits and dashes
        if ( ! isMatched("[\\d -]*", inputValue) ) {
            return false;
        }
        String value = inputValue.replaceAll("\\D", "");
        final int length = value.length();
        if ( 13 > length || 19 < length){
            return false;
        }else{
            return matchLuhn(value, length);
        }

    }

    public static boolean matchLuhn(String rawCardNumbers, int length){
        char cDigit;
        int nCheck = 0, nDigit;
        boolean bEven = false;
        for ( int n = length - 1; n >= 0; n--) {
            cDigit = rawCardNumbers.charAt(n);
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
