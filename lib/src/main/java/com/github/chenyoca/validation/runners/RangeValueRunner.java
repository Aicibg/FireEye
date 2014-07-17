package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class RangeValueRunner extends ValueTestRunner{

    public RangeValueRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    protected boolean withExtraInt(double inputValue) {
        return extraInt[0] <= inputValue && inputValue <= extraInt[1];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return extraFloat[0] <= inputValue && inputValue <= extraFloat[1];
    }
}
