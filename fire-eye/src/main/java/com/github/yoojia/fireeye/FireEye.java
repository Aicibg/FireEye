package com.github.yoojia.fireeye;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
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

    private final Context mContext;
    private final List<TypeWrapper> mOrderedFields = new ArrayList<>();
    private final SparseArray<StaticPatternInvoker> mStaticPatterns = new SparseArray<StaticPatternInvoker>();
    private final SparseArray<ValuePatternInvoker> mValuePatterns = new SparseArray<ValuePatternInvoker>();
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

    public FireEye(Context context) {
        mContext = context;
    }

    /**
     * 添加一个需要校验的View，并指定它的静态校验模式。
     * @param inputView TextView及其子类
     * @param patterns 数值校验模式
     * @return FireEye
     */
    public FireEye add(TextView inputView, StaticPattern...patterns){
        enforceViewNotNull(inputView);
        enforceHasPatterns(patterns);
        final int viewKey = inputView.hashCode();
        final StaticPatternInvoker invoker = mStaticPatterns.get(viewKey);
        // 校验配置不存在，则创建并添加；如果已存在，则添加到已有的配置中
        // 在创建新的输入框校验配置时，根据代码添加先后，将校验顺序保存下来：
        if (invoker == null){
            mOrderedFields.add(new TypeWrapper(viewKey, true));
            mStaticPatterns.put(viewKey, new StaticPatternInvoker(mContext, viewKey, inputView, patterns));
        }else{
            invoker.addPatterns(patterns);
        }
        return this;
    }

    /**
     * 添加一个需要校验的View，并指定它的数值校验模式。
     * @param inputView TextView及其子类
     * @param patterns 数值校验模式
     * @return FireEye
     */
    public FireEye add(TextView inputView, ValuePattern...patterns){
        enforceViewNotNull(inputView);
        enforceHasPatterns(patterns);
        final int viewKey = inputView.hashCode();
        final ValuePatternInvoker invoker = mValuePatterns.get(viewKey);
        // 校验配置不存在，则创建并添加；如果已存在，则添加到已有的配置中
        // 在创建新的输入框校验配置时，根据代码添加先后，将校验顺序保存下来：
        if (invoker == null){
            mOrderedFields.add(new TypeWrapper(viewKey, false));
            mValuePatterns.put(viewKey, new ValuePatternInvoker(mContext, viewKey, inputView, patterns));
        }else{
            invoker.addPatterns(patterns);
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
            final PatternInvoker invoker;
            if (typeWrapper.isStaticPattern){
                invoker = mStaticPatterns.get(typeWrapper.viewKey);
            }else{
                invoker = mValuePatterns.get(typeWrapper.viewKey);
            }
            final Result result = testPattern(invoker);
            if (result != null) return result;
        }
        return Result.passed(null);
    }

    public final void dump(){
        for (TypeWrapper wrapper : mOrderedFields){
            final PatternInvoker invoker;
            if (wrapper.isStaticPattern){
                invoker = mStaticPatterns.get(wrapper.viewKey);
            }else{
                invoker = mValuePatterns.get(wrapper.viewKey);
            }
            Log.d("FireEye", invoker.dump());
        }
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
    private Result testPattern(PatternInvoker invoker){
        mMessageDisplay.dismiss(invoker.input);
        final Result result = invoker.performTest();
        if (!result.passed){
            mMessageDisplay.show(invoker.input, result.message);
        }
        return result.passed ? null : result;
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

    private static void enforceViewNotNull(TextView view){
        if (view == null) throw new IllegalArgumentException("校验的View不能为空(Target view cannot be null)");
    }

    private final class TypeWrapper {

        final int viewKey;
        final boolean isStaticPattern;

        private TypeWrapper(int viewKey, boolean isStaticPattern) {
            this.viewKey = viewKey;
            this.isStaticPattern = isStaticPattern;
        }

    }
}
