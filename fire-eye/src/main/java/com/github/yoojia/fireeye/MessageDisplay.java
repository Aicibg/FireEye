package com.github.yoojia.fireeye;

import android.widget.TextView;

/**
 * @author Yoojia.chen (yooJia.Chen@gmail.com)
 * 2014-07-14
 */
public interface MessageDisplay {
    /**
     * Dismiss the setMessage
     * @param field Target view.
     */
    void dismiss(TextView field);

    /**
     * Show the setMessage
     * @param field Target view.
     * @param message Message to show.
     */
    void show(TextView field, String message);
}
