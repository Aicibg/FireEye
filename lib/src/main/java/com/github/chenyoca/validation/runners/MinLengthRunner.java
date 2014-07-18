package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in min
 */
public class MinLengthRunner extends TestRunner{

    public MinLengthRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return inputValue.length() >= extraLong[0];
    }

    @Override
    public void onAdded() {
        checkIntValues("MinLength Test");
    }
}
