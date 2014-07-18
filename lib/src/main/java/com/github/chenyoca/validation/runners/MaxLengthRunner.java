package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in max
 */
public class MaxLengthRunner extends TestRunner{

    public MaxLengthRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return inputValue.length() <= extraLong[0];
    }

    @Override
    public void onAdded() {
        checkIntValues("MaxLength Test");
    }
}
