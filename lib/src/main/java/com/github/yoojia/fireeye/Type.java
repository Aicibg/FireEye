package com.github.yoojia.fireeye;

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

    /**
     * Set date/time format. e.g: yyyy-MM-dd, HH:mm:ss, yyyy-MM-dd HH:mm:ss
     * @param format Date/Time format
     * @return Type
     */
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
                throw new UnsupportedOperationException(
                        "Only types of DATE/TIME can call this method to set date/time format string. ");
        }
        return this;
    }

    /**
     * Set time base for future/past
     * @param base time base
     * @return Type
     */
    public Type than(String base){
        // check type
        switch (this){
            case IsFuture:
            case IsPast:
                break;
            default:
                throw new UnsupportedOperationException(
                        "Only types of Type.IsFuture/Type.IsPast can call this method to set base time. ");
        }
        // value1: format
        // value2: base time
        values(null, base);
        return this;
    }

    public Type value(String value){
        values(value, null);
        return this;
    }

    public Type values(String value1, String value2){
        if (stringValues == null){
            stringValues = new String[]{value1, value2};
        }else{
            stringValues[0] = value1 == null ? stringValues[0] : value1;
            stringValues[1] = value2 == null ? stringValues[1] : value2;
        }
        return this;
    }

    public Type value(long value){
        longValues = new long[]{value};
        return this;
    }

    public Type values(long value1, long value2){
        if (longValues == null){
            longValues = new long[]{value1, value2};
        }else{
            longValues[0] = value1;
            longValues[1] = value2;
        }
        return this;
    }

    public Type value(double value){
        values(value, Double.NaN);
        return this;
    }

    public Type values(double value1, double value2){
        if (floatValues == null){
            floatValues = new double[]{value1, value2};
        }else{
            floatValues[0] = Double.isNaN(value1) ? floatValues[0]: value1;
            floatValues[1] = Double.isNaN(value2) ? floatValues[1]: value2;
        }
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

    @Override
    public String toString() {
        return "Type[:" + name() + "]";
    }
}
