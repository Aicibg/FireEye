package com.github.chenyoca.validation;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 * Lazy loader of the test extra value
 */
public interface ValuesLoader {

    /**
     * @return Return the INT value, size of 1 or 2.
     */
    long[] longValues();

    /**
     * @return Return the DOUBLE value, size of 1 or 2.
     */
    double[] doubleValues();

    /**
     * @return Return the STRING value, size of 1 or 2.
     */
    String[] stringValues();
}
