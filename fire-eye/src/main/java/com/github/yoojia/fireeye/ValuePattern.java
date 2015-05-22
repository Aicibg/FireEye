package com.github.yoojia.fireeye;

/**
 * 数值匹配模式。作为配置项传递，其数据由Config来保存
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
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

    String message;
    int messageId = -1;

    LazyLoader lazyLoader;
    ValueType valueType;
    String minValue;
    String maxValue;
    String value;

    private ValuePattern(String message){
        this.message = message;
    }

    /**
     * 设置懒加载接口
     * @param lazyLoader 懒加载接口
     * @return ValuePattern实例
     */
    public ValuePattern lazy(LazyLoader lazyLoader) {
        this.lazyLoader = lazyLoader;
        return this;
    }

    /**
     * 设置第一个参数值
     * @param first 数值
     * @return ValuePattern
     */
    public ValuePattern setFirstValue(double first){
        enforceFloatValueType();
        syncValue(value);
        return this;
    }

    /**
     * 设置第一个参数值
     * @param first 数值
     * @return ValuePattern
     */
    public ValuePattern setFirstValue(long first){
        enforceIntValueType();
        syncValue(value);
        return this;
    }

    /**
     * 设置第二个参数值
     * @param second 数值
     * @return ValuePattern
     */
    public ValuePattern setSecondValue(long second){
        enforceIntValueType();
        maxValue = String.valueOf(second);
        return this;
    }

    /**
     * 设置第二个参数值
     * @param second 数值
     * @return ValuePattern
     */
    public ValuePattern setSecondValue(double second){
        enforceFloatValueType();
        maxValue = String.valueOf(second);
        return this;
    }

    /**
     * 设置第一个参数值
     * @param value 数值
     * @return ValuePattern
     */
    public ValuePattern setValue(String value){
        syncValue(value);
        valueType = ValueType.String;
        return this;
    }

    /**
     * 设置第一个参数值
     * @param value 数值
     * @return ValuePattern
     */
    public ValuePattern setValue(long value){
        syncValue(value);
        valueType = ValueType.Int;
        return this;
    }

    /**
     * 设置第一个参数值
     * @param value 数值
     * @return ValuePattern
     */
    public ValuePattern setValue(double value){
        syncValue(value);
        valueType = ValueType.Float;
        return this;
    }

    /**
     * 设置提示消息内容
     * @param message 消息内容
     */
    public ValuePattern setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置提示消息内容的资源ID
     * @param msgId 资源ID
     */
    public ValuePattern setMessage(int msgId){
        messageId = msgId;
        return this;
    }

    private void syncValue(Object value){
        this.value = String.valueOf(value);
        this.minValue = this.value;
    }

    private void enforceIntValueType(){
        if (valueType == null){
            valueType = ValueType.Int;
        }else{
            if (!ValueType.Int.equals(valueType)) throw new IllegalArgumentException("设置的数值类型必须同为整数");
        }
    }

    private void enforceFloatValueType(){
        if (valueType == null){
            valueType = ValueType.Float;
        }else{
            if (!ValueType.Float.equals(valueType)) throw new IllegalArgumentException("设置的数值类型必须同为浮点数");
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name() + '\'' +
                ", message='" + message + '\'' +
                ", messageId=" + messageId +
                ", lazyLoader=" + lazyLoader +
                ", valueType=" + valueType +
                ", minValue='" + minValue + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
