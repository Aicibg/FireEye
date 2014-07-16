package com.github.chenyoca.validation;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 * Lazy loader of the test extra values
 */
public interface LazyLoader {

    /**
     * @return Return the INT values, size of 1 or 2.
     */
    int[] intValues();

    /**
     * @return Return the DOUBLE values, size of 1 or 2.
     */
    double[] doubleValues();

    /**
     * @return Return the STRING values, size of 1 or 2.
     */
    String[] stringValues();
}
