package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Only digits
 */
class DigitsRunner extends TestRunner {

    public DigitsRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
