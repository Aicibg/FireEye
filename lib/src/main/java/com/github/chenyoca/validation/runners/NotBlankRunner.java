package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Not blank runner
 */
public class NotBlankRunner extends TestRunner{

    public NotBlankRunner(Type testType, String message){
        super(testType, message);
    }


    @Override
    public boolean test(String inputValue) {
        boolean empty = TextUtils.isEmpty(inputValue);
        return ! empty && ! isMatched("^\\s*$", inputValue);
    }
}
