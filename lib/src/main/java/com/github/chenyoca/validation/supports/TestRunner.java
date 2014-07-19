package com.github.chenyoca.validation.supports;

import com.github.chenyoca.validation.ValuesLoader;
import com.github.chenyoca.validation.Type;

import java.util.regex.Pattern;

/**
 * AUTH: YooJia.Chen (Yoojia.Chen@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    protected enum ExtraType {
        Int, Float, String
    }

    public final Type testType;
    protected final double[] extraFloat = new double[2];
    // Access Level : Public, For InputType used this properties.
    public final long[] extraLong = new long[2];
    protected final String[] extraString = new String[2];

    protected ExtraType extraType = ExtraType.String;
    protected String message;
    private ValuesLoader valuesLoader;

    protected TestRunner(Type testType, String message){
        this.testType = testType;
        this.message = message;
    }

    public TestRunner(String message){
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
        return test(input);
    }

    /**
     * Test implement for sub class
     * @param inputValue Input value
     * @return True if passed, false otherwise.
     */
    protected abstract boolean test(String inputValue);

    /**
     * Call when runner finish config, added the the test runner array.
     */
    public void onAdded(){}

    /**
     * Check if set Int/Float extra value for test
     * @param name Name of test runner
     */
    protected void checkIntFloatValues(String name){
        if (ExtraType.String.equals(extraType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int/Long/Float/Double values." +
                    " Set by 'Type.TYPE.value(...) / Type.TYPE.values(...)'.");
    }

    /**
     * Check if set Int extra value for test
     * @param name Name of test runner
     */
    protected void checkIntValues(String name){
        if (!ExtraType.Int.equals(extraType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int/Long values." +
                    " Set by 'Type.TYPE.value(...) / Type.TYPE.values(...)'.");
    }

    /**
     * Format the message with extra value
     */
    protected void formatMessage(){
        if (message == null) return;
        switch (extraType){
            case Float:
                message = message.replace("{$1}","" + extraFloat[0])
                        .replace("{$2}","" + extraFloat[1]);
                break;
            case Int:
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
            setIfNeedValues(valuesLoader.longValues(),
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

    public void setRequiredValues(long[] lVals, String[] sVals, double[] dVals){
        boolean nullValues = lVals == null || lVals.length ==0;
        if ( ! nullValues) setValues(lVals);
        boolean nullDVs = dVals == null || dVals.length == 0;
        if ( ! nullDVs) setValues(dVals);
        nullValues &= nullDVs;
        boolean nullSVs = sVals == null || sVals.length == 0;
        if ( ! nullSVs) setValues(sVals);
        nullValues &= nullSVs;
        if (nullValues){
            throw
                    new IllegalArgumentException("Test required 1 or 2 values. " +
                            "Accept types: Int/Long/Float/Double/String .");
        }
    }

    public void setIfNeedValues(long[] lVals, String[] sVals, double[] dVals){
        if (lVals != null && lVals.length >0) setValues(lVals);
        if (sVals != null && sVals.length >0) setValues(sVals);
        if (dVals != null && dVals.length >0) setValues(dVals);
    }

    /**
     * Set Int extra value for test runner.
     * @param values Int extra
     */
    private void setValues(long... values){
        extraType = ExtraType.Int;
        if ( 1 == values.length){
            extraLong[0] = values[0];
        }else{
            extraLong[0] = values[0];
            extraLong[1] = values[1];
        }
    }

    /**
     * Set String extra value for test runner.
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
     * Set Flow/Double extra value for test runner.
     * @param values Flow/Double extra
     */
    private void setValues(double... values){
        extraType = ExtraType.Float;
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
