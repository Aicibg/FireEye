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
        checkIntValues("MaxLength Test");
        int maxLength = extraInt[0];
        return inputValue.length() <= maxLength;
    }

}
