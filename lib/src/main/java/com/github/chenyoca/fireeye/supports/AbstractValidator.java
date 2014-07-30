package com.github.chenyoca.fireeye.supports;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.ValuesLoader;

import java.util.regex.Pattern;

/**
 * AUTH: YooJia.Chen (Yoojia.Chen@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class AbstractValidator {

    protected enum ExtraType {
        Long, Double, String,None
    }

    public final Type testType;
    protected final double[] extraFloat = new double[2];
    // Access Level : Public, For InputType used this properties.
    public final long[] extraLong = new long[2];
    protected final String[] extraString = new String[2];

    protected ExtraType extraType = ExtraType.None;
    protected String message;
    private ValuesLoader valuesLoader;

    protected AbstractValidator(Type testType, String message){
        this.testType = testType;
        this.message = message;
    }

    public AbstractValidator(String message){
        this.testType = Type.Custom;
        this.message = message;
    }

    /**
     * Perform Test
     * @param input Input value
     * @return True if passed, false otherwise.
     */
    public boolean perform(String input){
        performLazyLoader();
        formatMessage();
        return isValid(input);
    }

    /**
     * Test implement for sub class
     * @param inputValue Input value
     * @return True if passed, false otherwise.
     */
    protected abstract boolean isValid(String inputValue);

    /**
     * Call when runner finish config, added the the isValid runner array.
     */
    public void verifyValues(){}

    /**
     * Check if set Long/Double extra value for isValid
     */
    protected void checkRequiredLongFloatValues(){
        final boolean isLF = ExtraType.Long.equals(extraType) || ExtraType.Double.equals(extraType);
        if (!isLF) throw new IllegalArgumentException(getClass().getSimpleName() +
                    " ONLY accept Int/Long/Float/Double values." +
                    " Set by 'Type.TYPE.value(...) / Type.TYPE.values(...)'.");
    }

    /**
     * Check if set Long extra value for isValid
     */
    protected void checkRequiredLongValues(){
        if (!ExtraType.Long.equals(extraType))
            throw new IllegalArgumentException(getClass().getSimpleName() +
                    " ONLY accept Int/Long values." +
                    " Set by 'Type.TYPE.value(...) / Type.TYPE.values(...)'.");
    }

    /**
     * Format the message with extra value
     */
    protected void formatMessage(){
        if (message == null) return;
        switch (extraType){
            case Double:
                message = message.replace("{$1}","" + extraFloat[0])
                        .replace("{$2}","" + extraFloat[1]);
                break;
            case Long:
                message = message.replace("{$1}","" + extraLong[0])
                        .replace("{$2}","" + extraLong[1]);
                break;
            case String:
                if (extraString[0] != null){
                    message = message.replace("{$1}", extraString[0]);
                }
                if (extraString[1] != null){
                    message = message.replace("{$2}", extraString[1]);
                }
                break;
        }
    }

    /**
     * Reload extra value from value loader
     */
    private void performLazyLoader(){
        if (valuesLoader != null){
            setValues(valuesLoader.longValues(),
                    valuesLoader.stringValues(),
                    valuesLoader.doubleValues());
        }
    }

    public String getMessage(){
        return message == null? "" : message;
    }

    public void setValuesLoader(ValuesLoader valuesLoader){
        this.valuesLoader = valuesLoader;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setValues(long[] lVals, String[] sVals, double[] dVals){
        if (lVals != null && lVals.length >0) setValues(lVals);
        if (sVals != null && sVals.length >0) setValues(sVals);
        if (dVals != null && dVals.length >0) setValues(dVals);
    }

    /**
     * Set Long extra value for isValid runner.
     * @param values Long extra
     */
    private void setValues(long... values){
        extraType = ExtraType.Long;
        if ( 1 == values.length){
            extraLong[0] = values[0];
        }else{
            extraLong[0] = values[0];
            extraLong[1] = values[1];
        }
    }

    /**
     * Set String extra value for isValid runner.
     * @param values String extra
     */
    private void setValues(String... values){
        extraType = ExtraType.String;
        if ( 1 == values.length){
            extraString[0] = values[0];
        }else{
            extraString[0] = values[0];
            extraString[1] = values[1];
        }
    }

    /**
     * Set Flow/Double extra value for isValid runner.
     * @param values Flow/Double extra
     */
    private void setValues(double... values){
        extraType = ExtraType.Double;
        if ( 1 == values.length){
            extraFloat[0] = values[0];
        }else{
            extraFloat[0] = values[0];
            extraFloat[1] = values[1];
        }
    }

    protected static boolean isMatched(String regex, CharSequence inputValue){
        Pattern p = Pattern.compile(regex);
        return p.matcher(inputValue).matches();
    }
}
