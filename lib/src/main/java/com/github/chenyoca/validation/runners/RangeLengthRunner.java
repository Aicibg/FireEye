package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in range
 */
public class RangeLengthRunner extends TestRunner{

    public RangeLengthRunner(){
        super("请输入长度在[{$1},{2$}]之间的内容！");
    }

    @Override
    public boolean test(CharSequence inputValue) {
        if (usingValuesType != USING_INTEGER_VALUES) throw new IllegalArgumentException("RangeLength Test ONLY accept int parameters!");
        formatMessage();
        int minLength = iValue1;
        int maxLength = iValue2;
        int length = inputValue.length();
        return minLength <= length && length <= maxLength;
    }

}
