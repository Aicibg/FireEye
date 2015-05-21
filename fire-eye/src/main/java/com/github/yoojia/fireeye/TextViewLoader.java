package com.github.yoojia.fireeye;

import android.widget.TextView;

/**
 * TextView Loader
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.0
 */
public class TextViewLoader implements LazyLoader{

    private final TextView mTextView;

    public TextViewLoader(TextView textView) {
        mTextView = textView;
    }

    @Override
    public Long loadInt() {
        return null;
    }

    @Override
    public Double loadFloat() {
        return null;
    }

    @Override
    public String loadString() {
        return mTextView.getText().toString();
    }
}
