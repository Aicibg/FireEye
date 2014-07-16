package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in max
 */
public class MaxValueRunner extends ValueTestRunner{

    public MaxValueRunner(String message){
        super(message);
    }

    @Override
    protected boolean testWithIntValues(double inputValue) {
        return inputValue <= extraIntValue1;
    }

    @Override
    protected boolean testWithFloatValues(double inputValue) {
        return inputValue <= extraFloatValue1;
    }
}
