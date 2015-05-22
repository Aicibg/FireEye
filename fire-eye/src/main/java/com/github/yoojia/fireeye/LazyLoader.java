package com.github.yoojia.fireeye;

/**
 * Lazy loader
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public interface LazyLoader {

    /**
     * @return 返回整型数值。如果没有数值，返回null。
     */
    Long loadInt();

    /**
     * @return 返回浮点型数值。如果没有数值，返回null。
     */
    Double loadFloat();

    /**
     * @return 返回字符串数值。如果没有数值，返回null。
     */
    String loadString();
}
