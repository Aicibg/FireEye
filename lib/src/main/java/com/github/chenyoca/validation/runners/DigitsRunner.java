package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Only digits
 */
public class DigitsRunner extends TestRunner{


    public DigitsRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
