package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.LazyLoader;

import java.util.regex.Pattern;

/**
 * AUTH: YooJia.Chen (Yoojia.Chen@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    protected enum ValuesType{
        Int, Float, String
    }

    protected ValuesType valuesType = ValuesType.String;

    protected String message;

    protected final double[] extraFloat = new double[2];

    // Access Level : Public, For InputType used this properties.
    public final int[] extraInt = new int[2];
    protected final String[] extraStringValues = new String[2];

    private LazyLoader lazyLoader;

    public TestRunner(String message){
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
     * Check if set Int/Flow extra values for test
     * @param name Name of test runner
     */
    protected void checkIntFlowValues(String name){
        if (ValuesType.String.equals(valuesType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int/Float/Double values( set by 'setValues(...)' )!");
    }

    /**
     * Check if set Int extra values for test
     * @param name Name of test runner
     */
    protected void checkIntValues(String name){
        if (!ValuesType.Int.equals(valuesType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int values( set by 'setValues(...)' ) !");
    }

    /**
     * Format the message with extra values
     */
    protected void formatMessage(){
        if (message == null) return;
        switch (valuesType){
            case Float:
                message = message.replace("{$1}","" + extraFloat[0])
                        .replace("{$2}","" + extraFloat[1]);
                break;
            case Int:
                message = message.replace("{$1}","" + extraInt[0])
                        .replace("{$2}","" + extraInt[1]);
                break;
            case String:
                if (extraStringValues[0] != null){
                    message = message.replace("{$1}", extraStringValues[0]);
                }
                if (extraStringValues[1] != null){
                    message = message.replace("{$2}", extraStringValues[1]);
                }
                break;
        }
    }

    /**
     * Reload extra values from lazy loader
     */
    private void performLazyLoader(){
        if (lazyLoader != null){
            setValues(lazyLoader.intValues());
            setValues(lazyLoader.doubleValues());
            setValues(lazyLoader.stringValues());
        }
    }

    /**
     * Call when test runner finish configuration and added to runners array.
     * In this method, Impl Runner can check extra values and values type.
     */
    public void onAdded(){}


    public String getMessage(){
        return message == null? "" : message;
    }

    public void setLazyLoader(LazyLoader lazyloader){
        this.lazyLoader = lazyloader;
    }

    public void setMessage(String message){
        if (message != null) this.message = message;
    }

    /**
     * Set Int extra values for test runner.
     * @param values Int extra
     */
    public void setValues(int... values){
        checkValuesLength(values.length);
        valuesType = ValuesType.Int;
        if ( 1 == values.length){
            extraInt[0] = values[0];
        }else{
            extraInt[0] = values[0];
            extraInt[1] = values[1];
        }
    }

    /**
     * Set String extra values for test runner.
     * @param values String extra
     */
    public void setValues(String... values){
        checkValuesLength(values.length);
        valuesType = ValuesType.String;
        if ( 1 == values.length){
            extraStringValues[0] = values[0];
        }else{
            extraStringValues[0] = values[0];
            extraStringValues[1] = values[1];
        }
    }

    /**
     * Set Flow/Double extra values for test runner.
     * @param values Flow/Double extra
     */
    public void setValues(double... values){
        checkValuesLength(values.length);
        valuesType = ValuesType.Float;
        if ( 1 == values.length){
            extraFloat[0] = values[0];
        }else if ( 2 == values.length){
            extraFloat[0] = values[0];
            extraFloat[1] = values[1];
        }
    }

    private void checkValuesLength(int length){
        if (length == 0 || length > 2){
            throw new IllegalArgumentException("Test extra values ONLY accept 1 or 2 values.");
        }
    }

    protected static boolean isMatched(String regex, CharSequence inputValue){
        Pattern p = Pattern.compile(regex);
        return p.matcher(inputValue).matches();
    }
}
