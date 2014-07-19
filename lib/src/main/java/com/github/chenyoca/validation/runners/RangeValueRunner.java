package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
class RangeValueRunner extends ValueTestRunner{

    public RangeValueRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    protected boolean withExtraLong(double inputValue) {
        return extraLong[0] <= inputValue && inputValue <= extraLong[1];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return extraFloat[0] <= inputValue && inputValue <= extraFloat[1];
    }
}
