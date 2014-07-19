package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.TestRunner;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-16
 */
abstract class ValueTestRunner extends TestRunner {

    public ValueTestRunner(Type testType, String message) {
        super(testType, message);
    }

    @Override
    protected boolean test(String input) {
        double inputValue;
        try{
            inputValue = Double.valueOf(input);
        }catch (Exception e){
            message = e.getMessage();
            return false;
        }
        if (ExtraType.Int.equals(extraType)){
            return withExtraLong(inputValue);
        }else{
            return withExtraFloat(inputValue);
        }
    }

    @Override
    public void onAdded() {
        checkIntFloatValues("ValueTest ");
    }

    protected abstract boolean withExtraLong(double inputValue);
    protected abstract boolean withExtraFloat(double inputValue);
}
