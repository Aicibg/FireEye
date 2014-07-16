package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in max
 */
public class MinValueRunner extends ValueTestRunner{

    public MinValueRunner(String message){
        super(message);
    }

    @Override
    protected boolean withExtraInt(double inputValue) {
        return inputValue >= extraInt[0];
    }

    @Override
    protected boolean withExtraFloat(double inputValue) {
        return inputValue >= extraFloat[0];
    }
}
