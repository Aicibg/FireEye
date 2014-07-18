package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in max
 */
public class MaxValueRunner extends ValueTestRunner{

    public MaxValueRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    protected boolean withExtraInt(double inputValue) {
        return inputValue <= extraLong[0];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return inputValue <= extraFloat[0];
    }
}
