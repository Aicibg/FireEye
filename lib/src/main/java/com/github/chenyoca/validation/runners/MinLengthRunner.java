package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Length in min
 */
public class MinLengthRunner extends TestRunner{

    public MinLengthRunner(){
        super("请输入长度大于{$1}的内容！");
    }

    @Override
    public boolean test(String inputValue) {
        checkIntValues("MinLength Test");
        int minLength = intValue1;
        return inputValue.length() >= minLength;
    }

}
