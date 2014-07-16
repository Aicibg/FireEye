package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class RangeValueRunner extends ValueTestRunner{

    public RangeValueRunner(){
        super("请输入在[{$1},{$2}]的数值！");
    }

    @Override
    protected boolean testWithIntValues(double inputValue) {
        return intValue1 <= inputValue && inputValue <= intValue2;
    }

    @Override
    protected boolean testWithFloatValues(double inputValue) {
        return floatValue1 <= inputValue && inputValue <= floatValue2;
    }
}
