package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in max
 */
public class MaxLengthRunner extends TestRunner{

    public MaxLengthRunner(){
        super("请输入长度小于{$1}的内容！");
    }

    @Override
    public boolean test(String inputValue) {
        checkIntValues("MaxLength Test");
        int maxLength = intValue1;
        return inputValue.length() <= maxLength;
    }

}
