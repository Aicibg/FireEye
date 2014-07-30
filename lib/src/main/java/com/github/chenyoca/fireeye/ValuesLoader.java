package com.github.chenyoca.fireeye;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 * Lazy loader of the isValid extra value
 */
public interface ValuesLoader {

    /**
     * @return Return the Int/Long values, size of 1 or 2.
     */
    long[] longValues();

    /**
     * @return Return the Float/Double values, size of 1 or 2.
     */
    double[] doubleValues();

    /**
     * @return Return the String values, size of 1 or 2.
     */
    String[] stringValues();
}
