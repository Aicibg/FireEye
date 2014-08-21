package com.github.chenyoca.fireeye;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.github.chenyoca.fireeye.supports.AbstractValidator;
import com.github.chenyoca.fireeye.validators.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-18
 */
class _ {

    final MessageDisplay display;
    final TextView field;
    final List<AbstractValidator> runners = new ArrayList<AbstractValidator>(1);

    final TextWatcher textWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int a, int b, int c) { }
        @Override public void onTextChanged(CharSequence s, int a, int b, int c) {}
        @Override public void afterTextChanged(Editable s) {
            field.setError(null);
        }
    };

    _(MessageDisplay display, final TextView field, AbstractValidator validator) {
        this.display = display;
        assert this.display != null;
        this.field = field;
        assert this.field != null;
        add(validator);
        final boolean isEditTextChildren = field instanceof EditText;
        if (! isEditTextChildren ){
            field.addTextChangedListener(textWatcher);
        }
    }

    TestResult performTest(){
        String value = String.valueOf(field.getText().toString());
        display.dismiss(field);
        String message;
        AbstractValidator first = runners.get(0);
        boolean required = false;
        if (first != null && Type.Required.equals(first.testType)){
            required = true;
            boolean passed = first.perform(value);
            message = first.getMessage();
            if ( ! passed){
                display.show(field, message);
                return new TestResult(false, message, null);
            }
        }else if (TextUtils.isEmpty(value)){
            return new TestResult(true, "NO_VALUE_NOT_REQUIRED", null);
        }

        final int size = runners.size();
        for (int i = required ? 1 : 0;i < size;i++){
            AbstractValidator r = runners.get(i);
            boolean passed = r.perform(value);
            message = r.getMessage();
            if ( ! passed){
                display.show(field, message);
                return new TestResult(false, message, value);
            }
        }
        return new TestResult(true, "TEST_PASSED", value);
    }

    void performInputType(){
        int inputType = field.getInputType();
        for (AbstractValidator r : runners){
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
                    final int index = Type.MaxLength.equals(r.testType) ? 0 : 1;
                    InputFilter[] origin = field.getFilters();
                    if (origin.length == 0){
                        field.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int)r.extraLong[index])});
                    }else if (origin.length == 1 && !(origin[0] instanceof InputFilter.LengthFilter)){
                        final InputFilter[] filters = new InputFilter[]
                                {
                                origin[0],
                                new InputFilter.LengthFilter((int)r.extraLong[index])
                                };
                        field.setFilters(filters);
                    }
                    break;
            }
        }
        field.setInputType(inputType);
    }

    void add(Context c, Type type){
        add(ValidatorFactory.build(c, type));
    }

    void add(AbstractValidator v){
        if (Type.Required.equals(v.testType)){
            runners.add(0, v);
        }else{
            runners.add(v);
        }
        v.setValues(v.testType.longValues, v.testType.stringValues, v.testType.floatValues);
        if (v.testType.message != null) v.setMessage(v.testType.message);
        if (v.testType.valuesLoader != null) v.setValuesLoader(v.testType.valuesLoader);
        v.verifyValues();
    }

}
