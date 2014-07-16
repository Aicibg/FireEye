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

    protected final double[] extraFloatValues = new double[2];
    protected final int[] extraIntValues = new int[2];
    protected final String[] extraStringValues = new String[2];

    private LazyLoader lazyLoader;

    public TestRunner(String message){
        this.message = message;
    }

    public boolean perform(String input){
        performLazyLoader();
        formatMessage();
        return test(input);
    }

    protected abstract boolean test(String inputValue);

    protected void checkIntFlowValues(String name){
        if (ValuesType.String.equals(valuesType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int/Float/Double values( set by 'setValues(...)' )!");
    }

    protected void checkIntValues(String name){
        if (!ValuesType.Int.equals(valuesType))
            throw new IllegalArgumentException(name +
                    " ONLY accept Int values( set by 'setValues(...)' ) !");
    }

    protected void formatMessage(){
        if (message == null) return;
        switch (valuesType){
            case Float:
                message = message.replace("{$1}","" + extraFloatValues[0])
                        .replace("{$2}","" + extraFloatValues[1]);
                break;
            case Int:
                message = message.replace("{$1}","" + extraIntValues[0])
                        .replace("{$2}","" + extraIntValues[1]);
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

    private void performLazyLoader(){
        if (lazyLoader != null){
            setValues(lazyLoader.intValues());
            setValues(lazyLoader.doubleValues());
            setValues(lazyLoader.stringValues());
        }
    }

    protected static boolean isMatched(String regex, CharSequence inputValue){
        Pattern p = Pattern.compile(regex);
        return p.matcher(inputValue).matches();
    }

    public String getMessage(){
        return message == null? "" : message;
    }

    public void setLazyLoader(LazyLoader lazyloader){
        this.lazyLoader = lazyloader;
    }

    public void setMessage(String message){
        if (message != null) this.message = message;
    }

    public void setValues(int... values){
        valuesType = ValuesType.Int;
        if (values.length > 0){
            if ( 1 == values.length){
                extraIntValues[0] = values[0];
            }else if ( 2 == values.length){
                extraIntValues[0] = values[0];
                extraIntValues[1] = values[1];
            }
        }
    }

    public void setValues(String... values){
        valuesType = ValuesType.String;
        if (values.length > 0){
            if ( 1 == values.length){
                extraStringValues[0] = values[0];
            }else if ( 2 == values.length){
                extraStringValues[0] = values[0];
                extraStringValues[1] = values[1];
            }
        }
    }

    public void setValues(double... values){
        valuesType = ValuesType.Float;
        if (values.length > 0){
            if ( 1 == values.length){
                extraFloatValues[0] = values[0];
            }else if ( 2 == values.length){
                extraFloatValues[0] = values[0];
                extraFloatValues[1] = values[1];
            }
        }
    }
}
