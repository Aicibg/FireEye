package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in min
 */
public class MinLengthRunner extends TestRunner{

    public MinLengthRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        checkIntValues("MinLength Test");
        int minLength = extraIntValue1;
        return inputValue.length() >= minLength;
    }

}
