package com.github.yoojia.fireeye;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入框的匹配模式
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
abstract class PatternMeta<T> {

    final List<T> patterns = new ArrayList<>();
    final TextView input;
    final int viewId;

    protected PatternMeta(int viewId, TextView input) {
        this.input = input;
        this.viewId = viewId;
    }

    public void addPatterns(T[] patterns){
        this.patterns.addAll(Arrays.asList(patterns));
    }

    /**
     * 校验输入
     * @return 校验结果
     */
    abstract Result performTest();

}
