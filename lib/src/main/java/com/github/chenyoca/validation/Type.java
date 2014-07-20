package com.github.chenyoca.validation;

/**
 * AUTH: chenyoca (chenyoca@gmail.com)
 * DATE: 2014-06-25
 * Test types.
 */
public enum Type {
    Custom,
    Required,
    NotBlank,
    Digits,
    IsDate,
    IsDateTime,
    IsTime,
    IsFuture,
    IsPast,
    Email,
    EqualsTo,
    Host,
    URL,
    IPv4,
    RangeLength,
    MinLength,
    MaxLength,
    Numeric,
    CreditCard,
    RangeValue,
    MinValue,
    MaxValue,
    MobilePhone;

    String[] stringValues = null;
    long[] longValues = null;
    double[] floatValues = null;
    String message = null;
    ValuesLoader valuesLoader = null;

    public Type format(String format){
        switch (this){
            case IsDate:
            case IsTime:
            case IsDateTime:
            case IsFuture:
            case IsPast:
                value(format);
                break;
            default:
                throw new IllegalStateException(
                        "Only types of DATE/TIME can call this method to set date format string. ");
        }
        return this;
    }

    public Type value(String value){
        stringValues = new String[]{value};
        return this;
    }

    public Type values(String value1, String value2){
        stringValues = new String[]{value1, value2};
        return this;
    }

    public Type value(long value){
        longValues = new long[]{value};
        return this;
    }

    public Type values(long value1, long value2){
        longValues = new long[]{value1, value2};
        return this;
    }

    public Type value(double value){
        floatValues = new double[]{value};
        return this;
    }

    public Type values(double value1, double value2){
        floatValues = new double[]{value1, value2};
        return this;
    }

    public Type value(ValuesLoader valuesLoader){
        this.valuesLoader = valuesLoader;
        return this;
    }

    public Type message(String message){
        this.message = message;
        return this;
    }
}
