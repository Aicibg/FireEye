package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualsToRunner extends TestRunner{

    public EqualsToRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        String value = null;
        switch (valuesType){
            case Int: value = String.valueOf(extraInt[0]); break;
            case Float: value = String.valueOf(extraFloat[0]); break;
            case String: value = extraStringValues[0]; break;
        }
        return inputValue.equals(value);
    }

}
