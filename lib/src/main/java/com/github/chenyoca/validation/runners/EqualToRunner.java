package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Equal to a value
 */
public class EqualToRunner extends TestRunner{

    public EqualToRunner(){
        super("请输入相同的内容！");
    }

    @Override
    public boolean test(String inputValue) {
        String value = null;
        switch (valuesType){
            case Int: value = String.valueOf(intValue1); break;
            case Float: value = String.valueOf(floatValue1); break;
            case String: value = strValue1; break;
        }
        return inputValue.equals(value);
    }

}
