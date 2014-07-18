package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualsToRunner extends TestRunner{

    public EqualsToRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        String value = null;
        switch (extraType){
            case Int: value = String.valueOf(extraLong[0]); break;
            case Float: value = String.valueOf(extraFloat[0]); break;
            case String: value = extraString[0]; break;
        }
        return inputValue.equals(value);
    }

}
