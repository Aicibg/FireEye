package com.github.yoojia.fireeye.supports;

import android.view.View;
import android.widget.TextView;

import com.github.yoojia.fireeye.ValuesLoader;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class TextViewValuesLoader implements ValuesLoader {

    private final TextView editText;

    public TextViewValuesLoader(TextView editText){
        this.editText = editText;
    }

    public TextViewValuesLoader(View parent, int viewId){
        this((TextView)parent.findViewById(viewId));
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
