package com.github.yoojia.fireeye.validators;

import android.text.TextUtils;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Not blank runner
 */
class NotBlankValidator extends AbstractValidator {

    public NotBlankValidator(Type testType, String message){
        super(testType, message);
    }


    @Override
    public boolean isValid(String inputValue) {
        boolean empty = TextUtils.isEmpty(inputValue);
        return ! empty && ! isMatched("^\\s*$", inputValue);
    }
}
