package com.github.chenyoca.validation.supports;

import android.view.View;
import android.widget.EditText;

import com.github.chenyoca.validation.ValuesLoader;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class EditTextValuesLoader implements ValuesLoader {

    private final EditText editText;

    public EditTextValuesLoader(EditText editText){
        this.editText = editText;
    }

    public EditTextValuesLoader(View parent, int viewId){
        this((EditText)parent.findViewById(viewId));
    }

    @Override
    public long[] longValues() { return new long[0]; }

    @Override
    public double[] doubleValues() { return new double[0]; }

    @Override
    public String[] stringValues() {
        return new String[]{editText.getText().toString()};
    }
}
