package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in range
 */
class RangeLengthRunner extends TestRunner {

    public RangeLengthRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        int length = inputValue.length();
        return extraLong[0] <= length && length <= extraLong[1];
    }

    @Override
    public void onAdded() {
        checkIntValues("RangeLength Test");
    }
}
