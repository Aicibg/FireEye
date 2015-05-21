package com.github.yoojia.fireeye;

/**
 * 数值匹配模式
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since app.version
 */
public enum ValuePattern {

    Required("此为必填项目"),

    MaxLength("长度不能超过{0}"),
    MinLength("长度不能小于{0}"),
    RangeLength("长度必须在[{0},{1}]之间"),

    MaxValue("数值不能超过{0}"),
    MinValue("数值不能小于{0}"),
    RangeValue("数值必须在[{0},{1}]之间"),

    EqualsTo("必须输入相同内容");

    private String mMessage;
    private LazyLoader mLazyLoader;

    ValueType valueType;

    String minValue;
    String maxValue;
    String value;

    private ValuePattern(String message){
        mMessage = message;
    }

    public ValuePattern lazy(LazyLoader lazyLoader) {
        mLazyLoader = lazyLoader;
        return this;
    }

    /**
     * 设置提示信息
     * @param message 提示信息
     */
    public void setMessage(String message) {
        mMessage = message;
    }

    public ValuePattern setFirstValue(double min){
        enforceValueType();
        minValue = String.valueOf(min);
        this.value = minValue;
        return this;
    }

    public ValuePattern setSecondValue(double max){
        enforceValueType();
        maxValue = String.valueOf(max);
        return this;
    }

    public ValuePattern setFirstValue(long min){
        checkIntValue();
        minValue = String.valueOf(min);
        this.value = minValue;
        return this;
    }


    public ValuePattern setSecondValue(long max){
        checkIntValue();
        maxValue = String.valueOf(max);
        return this;
    }

    public ValuePattern setValue(String value){
        this.value = value;
        this.minValue = this.value;
        valueType = ValueType.String;
        return this;
    }

    public ValuePattern setValue(long value){
        this.value = String.valueOf(value);
        this.minValue = this.value;
        valueType = ValueType.Int;
        return this;
    }

    public ValuePattern setValue(double value){
        this.value = String.valueOf(value);
        this.minValue = this.value;
        valueType = ValueType.Float;
        return this;
    }

    private void checkIntValue(){
        if (valueType == null){
            valueType = ValueType.Int;
        }else{
            if (!ValueType.Int.equals(valueType)) throw new IllegalArgumentException("设置的数值类型必须同为整数");
        }
    }

    private void enforceValueType(){
        if (valueType == null){
            valueType = ValueType.Float;
        }else{
            if (!ValueType.Float.equals(valueType)) throw new IllegalArgumentException("设置的数值类型必须同为浮点数");
        }
    }

    void performLazyLoader(){
        if (mLazyLoader == null) return;
        final String stringValue = mLazyLoader.loadString();
        final Long longValue = mLazyLoader.loadInt();
        final Double floatValue = mLazyLoader.loadFloat();
        if (stringValue != null){
            setValue(stringValue);
        }
        else if (longValue != null){
            setValue(longValue);
        }
        else if (floatValue != null){
            setValue(floatValue);
        }
    }

    String getMessage() {
        if (mMessage == null) return null;
        String message = mMessage;
        if (minValue != null) message = message.replace("{0}", minValue);
        if (maxValue != null) message = message.replace("{1}", maxValue);
        return message;
    }

    enum ValueType{
        Float,
        Int,
        String
    }

}
