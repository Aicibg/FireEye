package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Value in range
 */
public class RangeValueRunner extends TestRunner{

    public RangeValueRunner(){
        super("请输入在[{$1},{$2}]的数值！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        return dispatch(inputValue);
    }

    @Override
    protected boolean testIntValue(int inputValue, int min, int max) {
        return min <= inputValue && inputValue <= max;
    }

    @Override
    protected boolean testDoubleValue(double inputValue, double min, double max) {
        return min <= inputValue && inputValue <= max;
    }

    @Override
    protected boolean testStringValue(String inputValue, String val1, String bal2) {
        throw new IllegalArgumentException("RangeValue Test ONLY accept int/double/float values!");
    }
}
