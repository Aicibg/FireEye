package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualToRunner extends TestRunner{

    public EqualToRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        String value = null;
        switch (valuesType){
            case Int: value = String.valueOf(extraIntValue1); break;
            case Float: value = String.valueOf(extraFloatValue1); break;
            case String: value = extraStringValue1; break;
        }
        return inputValue.equals(value);
    }

}
