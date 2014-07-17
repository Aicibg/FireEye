package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-16
 */
public abstract class ValueTestRunner extends TestRunner {

    public ValueTestRunner(Type testType, String message) {
        super(testType, message);
    }

    @Override
    protected boolean test(String input) {
        double inputValue = 0;
        try{
            inputValue = Double.valueOf(input);
        }catch (Exception e){
            message = e.getMessage();
            return false;
        }
        if (ExtraType.Int.equals(extraType)){
            return withExtraInt(inputValue);
        }else{
            return withExtraFloat(inputValue);
        }
    }

    @Override
    public void onAdded() {
        checkIntFlowValues("ValueTest ");
    }

    protected abstract boolean withExtraInt(double inputValue);
    protected abstract boolean withExtraFloat(double inputValue);
}
