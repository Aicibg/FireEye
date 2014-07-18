package com.github.chenyoca.validation;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.EditText;

import com.github.chenyoca.validation.supports.RunnerFactory;
import com.github.chenyoca.validation.runners.TestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-18
 */
public class _ {

    final MessageDisplay display;
    final EditText field;
    List<TestRunner> runners = new ArrayList<TestRunner>(1);

    public _(MessageDisplay display, EditText field, Type type) {
        this.display = display;
        this.field = field;
        add(field.getContext(), type);
    }

    public ResultWrapper performTest(){
        String value = String.valueOf(field.getText().toString());
        boolean passed = true;
        String message = null;
        TestRunner first = runners.get(0);
        if (first != null && Type.Required.equals(first.testType)){
            passed = first.perform(value);
            message = first.getMessage();
        }else if (TextUtils.isEmpty(value)){
            return new ResultWrapper(true, "NO-INPUT-VALUE-AND-IS-NOT-REQUIRED", value);
        }

        if ( ! passed){
            if (display != null) display.show(field, message);
            return new ResultWrapper(false, message, null);
        }

        final int size = runners.size();
        for (int i=1;i<size;i++){
            TestRunner r = runners.get(i);
            passed = r.perform(value);
            message = r.getMessage();
            if ( !passed){
                if (display != null) display.show(field, message);
                break;
            }
        }
        return new ResultWrapper(passed, message, value);
    }

    public void performInputType(){
        int inputType = InputType.TYPE_CLASS_TEXT;
        for (TestRunner r : runners){
            switch (r.testType){
                case MobilePhone:
                case Numeric:
                case Digits:
                case MaxValue:
                case MinValue:
                case RangeValue:
                case IPv4:
                case CreditCard:
                    inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL;
                    break;
                case Email:
                    inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                    field.setSingleLine(true);
                    break;
                case URL:
                case Host:
                    inputType = InputType.TYPE_TEXT_VARIATION_URI;
                    break;
                case MaxLength:
                case RangeLength:
                    int index = Type.MaxLength.equals(r.testType) ? 0 : 1;
                    field.setFilters(new InputFilter[]{
                            new InputFilter.LengthFilter((int)r.extraLong[index])} );
                    break;
                default: inputType = InputType.TYPE_CLASS_TEXT;
            }
        }
        field.setInputType(inputType);
    }

    public void add(Context c, Type type){
        TestRunner r = RunnerFactory.build(c, type);
        setValues(r, type);
        if (Type.Required.equals(type)){
            runners.add(0, r);
        }else{
            runners.add(r);
        }
    }

    void setValues(TestRunner r, Type type){
        switch (type){
            case MaxLength:
            case MinLength:
            case RangeLength:
                if (type.longValues != null) r.setValues(type.longValues);
                break;
            case MinValue:
            case MaxValue:
            case RangeValue:
                if (type.longValues != null) r.setValues(type.longValues);
                if (type.floatValues != null) r.setValues(type.floatValues);
                if (type.stringValues != null) r.setValues(type.stringValues);
                break;
            default: break;
        }
        if (type.message != null) r.setMessage(type.message);
        if (type.lazyLoader != null) r.setLazyLoader(type.lazyLoader);
    }
}
