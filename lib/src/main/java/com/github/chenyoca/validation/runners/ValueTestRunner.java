package com.github.chenyoca.validation.runners;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-16
 */
public abstract class ValueTestRunner extends TestRunner {

    public ValueTestRunner(String message) {
        super(message);
    }

    @Override
    protected boolean test(String input) {
        checkIntFlowValues("ValueTest ");
        double inputValue = 0;
        try{
            inputValue = Double.valueOf(input);
        }catch (Exception e){
            message = e.getMessage();
            return false;
        }
        if (ValuesType.Int.equals(valuesType)){
            return withExtraInt(inputValue);
        }else{
            return withExtraFloat(inputValue);
        }
    }

    protected abstract boolean withExtraInt(double inputValue);
    protected abstract boolean withExtraFloat(double inputValue);
}
