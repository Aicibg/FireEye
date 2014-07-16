package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.LazyLoader;

import java.util.regex.Pattern;

/**
 * AUTH: chenyoca (Yoojia.Chen@gmail.com)
 * DATE: 2014-06-25
 * Test runner.
 */
public abstract class TestRunner {

    protected enum ValuesType{
        Int, Float, String
    }

    protected ValuesType valuesType = ValuesType.String;

    protected String message;

    protected double floatValue1 = 0;
    protected double floatValue2 = 0;

    public int intValue1 = 0;
    public int intValue2 = 0;

    protected String strValue1 = null;
    protected String strValue2 = null;

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

//    protected boolean dispatch(CharSequence inputValue){
//        //
////        if (NumericRunner.isNumeric(String.valueOf(inputValue))){
////            System.out.println(">>> 输入的内容为数值型：" + inputValue);
////            System.out.println(">>> 参数类型为：" + valuesType.name());
////            if (ValuesType.Int.equals(valuesType)){
////                return performIntInput(inputValue);
////            }else{
////                return performDoubleInput(inputValue);
////            }
////        }else{
////            System.out.println(">>> 输入的内容不是数值型！！！");
////            return false;
////        }
//        //
//        switch (valuesType){
//            case Int:
//                return performIntInput(inputValue);
//
//            case Float:
//                return performDoubleInput(inputValue);
//
//            case String:
//                return testStringValue(String.valueOf(inputValue), strValue1, strValue2);
//
//            default: return false;
//        }
//    }

    protected void checkIntFlowValues(String name){
        if (ValuesType.String.equals(valuesType))
            throw new IllegalArgumentException(name + " ONLY accept Int/Float/Double values( set by 'setValues(...)' )!");
    }

    protected void checkIntValues(String name){
        if (!ValuesType.Int.equals(valuesType))
            throw new IllegalArgumentException(name + " ONLY accept Int values( set by 'setValues(...)' ) !");
    }

    protected void formatMessage(){
        if (message == null) return;
        switch (valuesType){
            case Float:
                message = message.replace("{$1}","" + floatValue1).replace("{$2}","" + floatValue2);
                break;
            case Int:
                message = message.replace("{$1}","" + intValue1).replace("{$2}","" + intValue2);
                break;
            case String:
                if (strValue1 != null) message = message.replace("{$1}", strValue1);
                if (strValue2 != null) message = message.replace("{$2}", strValue2);
                break;
        }
    }

    private void performLazyLoader(){
        if (lazyLoader != null){
            int[] iVs = lazyLoader.intValues();
            double[] dVs = lazyLoader.doubleValues();
            String[] sVs = lazyLoader.stringValues();

            if (iVs.length != 0) {
                valuesType = ValuesType.Int;
                if (iVs.length == 2){
                    intValue1 = iVs[0];
                    intValue2 = iVs[1];
                }else if (iVs.length == 1){
                    intValue1 = iVs[0];
                }
            }

            if (dVs.length != 0){
                valuesType = ValuesType.Float;
                if (dVs.length == 2){
                    floatValue1 = dVs[0];
                    floatValue2 = dVs[1];
                }else if (dVs.length == 1){
                    floatValue1 = dVs[0];
                }
            }

            if (sVs.length != 0){
               valuesType = ValuesType.String;
                if (sVs.length == 2){
                    strValue1 = sVs[0];
                    strValue1 = sVs[1];
                }else if (sVs.length == 1){
                    strValue1 = sVs[0];
                }
            }

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
                this.intValue1 = values[0];
            }else if ( 2 == values.length){
                this.intValue1 = values[0];
                this.intValue2 = values[1];
            }
        }
    }

    public void setValues(String... values){
        valuesType = ValuesType.String;
        if (values.length > 0){
            if ( 1 == values.length){
                this.strValue1 = values[0];
            }else if ( 2 == values.length){
                this.strValue1 = values[0];
                this.strValue2 = values[1];
            }
        }
    }

    public void setValues(double... values){
        valuesType = ValuesType.Float;
        if (values.length > 0){
            if ( 1 == values.length){
                this.floatValue1 = values[0];
            }else if ( 2 == values.length){
                this.floatValue1 = values[0];
                this.floatValue2 = values[1];
            }
        }
    }
}
