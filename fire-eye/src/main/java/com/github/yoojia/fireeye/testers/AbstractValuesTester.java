package com.github.yoojia.fireeye.testers;


/**
 * 外传数值校验器
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public abstract class AbstractValuesTester extends AbstractTester{

    protected long maxIntValue;
    protected long minIntValue;
    protected Long intValue;

    protected double maxFloatValue;
    protected double minFloatValue;
    protected Double floatValue;

    protected String stringValue;

    public void setMaxIntValue(long maxIntValue) {
        this.maxIntValue = maxIntValue;
    }

    public void setMinIntValue(long minIntValue) {
        this.minIntValue = minIntValue;
    }

    public void setIntValue(long intValue) {
        this.intValue = intValue;
    }

    public void setMaxFloatValue(double maxFloatValue) {
        this.maxFloatValue = maxFloatValue;
    }

    public void setMinFloatValue(double minFloatValue) {
        this.minFloatValue = minFloatValue;
    }

    public void setFloatValue(double floatValue) {
        this.floatValue = floatValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
}
