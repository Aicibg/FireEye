package com.github.yoojia.fireeye;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Fire Eye
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version 2015-05-20
 * @since   2.0
 */
public class FireEye {

    private final List<TypeWrapper> mOrderedFields = new ArrayList<>();
    private final SparseArray<StaticPatternMeta> mStaticPatterns = new SparseArray<StaticPatternMeta>();
    private final SparseArray<ValuePatternMeta> mValuePatterns = new SparseArray<ValuePatternMeta>();
    private final MessageDisplay mDefaultMessageDisplay = new MessageDisplay() {

        @Override
        public void dismiss(TextView input) {
            input.setError(null);
        }

        @Override
        public void show(TextView input, String message) {
            input.setError(message);
        }

    };

    private MessageDisplay mMessageDisplay = mDefaultMessageDisplay;

    /**
     * 添加一个需要校验的View，并指定它的静态校验模式。
     * @param viewWithId 必须带ID的TextView及其子类
     * @param patterns 数值校验模式
     * @return FireEye
     */
    public FireEye add(TextView viewWithId, StaticPattern...patterns){
        enforceHasViewId(viewWithId);
        enforceHasPatterns(patterns);
        final int viewId = viewWithId.getId();
        final StaticPatternMeta meta = mStaticPatterns.get(viewId);
        // 校验配置不存在，则创建并添加；如果已存在，则添加到已有的配置中
        // 在创建新的输入框校验配置时，根据代码添加先后，将校验顺序保存下来：
        if (meta == null){
            mOrderedFields.add(new TypeWrapper(viewId, true));
            mStaticPatterns.put(viewId, new StaticPatternMeta(viewId, viewWithId, patterns));
        }else{
            meta.addPatterns(patterns);
        }
        return this;
    }

    /**
     * 添加一个需要校验的View，并指定它的数值校验模式。
     * @param viewWithId 必须带ID的TextView及其子类
     * @param patterns 数值校验模式
     * @return FireEye
     */
    public FireEye add(TextView viewWithId, ValuePattern...patterns){
        enforceHasViewId(viewWithId);
        enforceHasPatterns(patterns);
        final int viewId = viewWithId.getId();
        final ValuePatternMeta meta = mValuePatterns.get(viewId);
        // 校验配置不存在，则创建并添加；如果已存在，则添加到已有的配置中
        // 在创建新的输入框校验配置时，根据代码添加先后，将校验顺序保存下来：
        if (meta == null){
            mOrderedFields.add(new TypeWrapper(viewId, false));
            mValuePatterns.put(viewId, new ValuePatternMeta(viewId, viewWithId, patterns));
        }else{
            meta.addPatterns(patterns);
        }
        return this;
    }

    /**
     * 设置校验不通过时的消息显示处理接口
     * @param messageDisplay 消息显示处理接口
     */
    public void setMessageDisplay(MessageDisplay messageDisplay){
        mMessageDisplay = messageDisplay;
    }

    /**
     * 校验输入，当检测到校验失败时中断校验。
     * @return 校验结果
     */
    public Result test(){
        // 校验时，按保存的的输入框顺序来校验
        for (TypeWrapper typeWrapper : mOrderedFields){
            final PatternMeta target;
            if (typeWrapper.isStaticPattern){
                target = mStaticPatterns.get(typeWrapper.viewId);
            }else{
                target = mValuePatterns.get(typeWrapper.viewId);
            }
            final Result result = testPattern(target);
            if (result != null) return result;
        }
        return Result.passed(null);
    }

    /**
     * 获取输入框的字符串内容
     * @param input 输入框
     * @return 字符串内容
     */
    public static String getStringValue(TextView input){
        return String.valueOf(input.getText());
    }

    /**
     * 获取输入框的整型值内容
     * @param input 输入框
     * @return 整型值
     */
    public static long getIntValue(TextView input){
        return Long.valueOf(getStringValue(input));
    }

    /**
     * 获取输入框的浮点值内容
     * @param input 输入框
     * @return 浮点值
     */
    public static double getFloatValue(TextView input){
        return Double.valueOf(getStringValue(input));
    }

    /**
     * @return Null if passed, otherwise return the result
     */
    private Result testPattern(PatternMeta meta){
        mMessageDisplay.dismiss(meta.input);
        final Result result = meta.performTest();
        if (!result.passed){
            mMessageDisplay.show(meta.input, result.message);
        }
        return result.passed ? null : result;
    }

    /**
     * 确保View有一个ID
     * @param viewWithId View
     */
    private static void enforceHasViewId(TextView viewWithId){
        final int viewId = viewWithId.getId();
        if (viewId == View.NO_ID){
            throw new IllegalArgumentException("检验的View必须具备一个Id (View Id required)");
        }
    }

    /**
     * 确保有一个校验模式
     * @param items 模式条目
     */
    private static void enforceHasPatterns(Object[] items){
        if (items == null || items.length == 0){
            throw new IllegalArgumentException("必须指定至少一个校验模式(Pattern required)");
        }
    }

    private final class TypeWrapper {

        final int viewId;
        final boolean isStaticPattern;

        private TypeWrapper(int viewId, boolean isStaticPattern) {
            this.viewId = viewId;
            this.isStaticPattern = isStaticPattern;
        }

    }
}
