package com.github.chenyoca.validation;

import android.widget.TextView;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 */
public interface MessageDisplay {
    /**
     * Dismiss the message
     * @param field Target view.
     */
    void dismiss(TextView field);

    /**
     * Show the message
     * @param field Target view.
     * @param message Message to show.
     */
    void show(TextView field, String message);
}
