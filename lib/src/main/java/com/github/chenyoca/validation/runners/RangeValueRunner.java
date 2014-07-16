package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class RangeValueRunner extends ValueTestRunner{

    public RangeValueRunner(String message){
        super(message);
    }

    @Override
    protected boolean testWithIntValues(double inputValue) {
        return extraIntValue1 <= inputValue && inputValue <= extraIntValue2;
    }

    @Override
    protected boolean testWithFloatValues(double inputValue) {
        return extraFloatValue1 <= inputValue && inputValue <= extraFloatValue2;
    }
}
