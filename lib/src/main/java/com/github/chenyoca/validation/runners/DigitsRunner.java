package com.github.chenyoca.validation.runners;

import android.text.TextUtils;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Only digits
 */
public class DigitsRunner extends TestRunner{


    public DigitsRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        return TextUtils.isDigitsOnly(inputValue);
    }

}
