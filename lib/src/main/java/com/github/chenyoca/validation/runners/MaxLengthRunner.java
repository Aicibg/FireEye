package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in max
 */
public class MaxLengthRunner extends TestRunner{

    public MaxLengthRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        return inputValue.length() <= extraInt[0];
    }

    @Override
    public void onAdded() {
        checkIntValues("MaxLength Test");
    }
}
