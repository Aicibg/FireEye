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

    protected final List<T> patterns = new ArrayList<>();

    public final TextView input;
    public final int viewId;

    protected PatternMeta(int viewId, TextView input) {
        this.input = input;
        this.viewId = viewId;
    }

    /**
     * 添加匹配模式列表
     * @param patterns 匹配模式列表
     */
    public final void addPatterns(T[] patterns){
        final List<T> array = Arrays.asList(patterns);
        for (T item : array){
            if (!onItemFilter(item)) this.patterns.add(item);
        }
    }

    /**
     * 校验输入
     * @return 校验结果
     */
    public abstract Result performTest();

    /**
     * 排序处理
     * @param item 当前正在添加的条目
     * @return 如果目标被手动排序，由返回true。
     */
    protected abstract boolean onItemFilter(T item);
}
