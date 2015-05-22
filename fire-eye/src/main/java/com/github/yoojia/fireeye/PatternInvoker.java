package com.github.yoojia.fireeye;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入框的匹配模式
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.3
 */
abstract class PatternInvoker<P, E> {

    protected final List<P> patterns = new ArrayList<>();
    protected final Context context;

    public final TextView input;
    public final int viewId;

    protected PatternInvoker(Context context, int viewId, TextView input) {
        this.context = context;
        this.input = input;
        this.viewId = viewId;
    }

    /**
     * 添加匹配模式列表
     * @param patterns 匹配模式列表
     */
    public final void addPatterns(E[] patterns){
        for (E item : Arrays.asList(patterns)){
            final P pattern = convert(item);
            if (!onFilter(pattern, item)){
                this.patterns.add(pattern);
            }
        }
    }

    /**
     * 校验输入
     * @return 校验结果
     */
    public abstract Result performTest();

    /**
     * 配置项过滤
     * @param pattern 转换后的储存项
     * @param item 配置项目
     * @return 如果配置项手动排序，则返回True。否则返回False。
     */
    protected abstract boolean onFilter(P pattern, E item);

    /**
     * 将配置项 E 转换成储存项 P
     * @param item 配置项 E
     * @return 储存项 P
     */
    protected abstract P convert(E item);
}
