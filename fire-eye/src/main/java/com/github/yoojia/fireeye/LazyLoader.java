package com.github.yoojia.fireeye;

/**
 * Lazy loader
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public interface LazyLoader {

    Long loadInt();

    Double loadFloat();

    String loadString();
}
