package com.github.yoojia.fireeye;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.github.yoojia.fireeye.testers.AbstractValuesTester;
import com.github.yoojia.fireeye.testers.EqualsToTester;
import com.github.yoojia.fireeye.testers.MaxLengthTester;
import com.github.yoojia.fireeye.testers.MaxValueTester;
import com.github.yoojia.fireeye.testers.MinLengthTester;
import com.github.yoojia.fireeye.testers.MinValueTester;
import com.github.yoojia.fireeye.testers.RangeLengthTester;
import com.github.yoojia.fireeye.testers.RangeValueTester;
import com.github.yoojia.fireeye.testers.RequiredValueTester;

/**
 * 输入框的匹配模式
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since   2.3
 */
final class ValuePatternInvoker extends PatternInvoker<ValuePatternMeta, ValuePattern> {

    private static final String TAG = "ValueInvoker";

    ValuePatternInvoker(Context context, int viewId, TextView field, ValuePattern... patterns) {
        super(context, viewId, field);
        addPatterns(patterns);
    }

    @Override
    public Result performTest(){
        final String value = input.getText().toString();
        // 当输入内容为空时，如果第一个校验模式不是“Required”，则跳过后面的配置。
        final ValuePatternMeta first = patterns.get(0);
        if (TextUtils.isEmpty(value) && !ValuePattern.Required.equals(first.pattern)){
            return Result.passed(null);
        }
        for (ValuePatternMeta pattern : patterns){
            pattern.performLazyLoader();
            final AbstractValuesTester tester = findTester(pattern.pattern);
            final boolean passed = tester.performTest(value);
            if (!passed){
                FireEyeEnv.log(TAG, tester.getName() + "Result > passed: NO, value: " + value + ", message: " + pattern.getMessage());
                // 如果校验器发生异常，取异常消息返回
                String message = tester.getExceptionMessage();
                if (message == null) message = pattern.getMessage();
                return Result.reject(message, value);
            }else{
                FireEyeEnv.log(TAG, tester.getName() + " > passed: YES, value: " + value);
            }
        }
        FireEyeEnv.log(TAG, "Input test result > passed: YES, value: " + value);
        return Result.passed(value);
    }

    @Override
    protected boolean onFilter(ValuePatternMeta meta, ValuePattern item) {
        if (ValuePattern.Required.equals(item)){
            this.patterns.add(0, meta);
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected ValuePatternMeta convert(ValuePattern item) {
        final ValuePatternMeta meta = ValuePatternMeta.parse(item);
        meta.convertMessage(context);
        FireEyeEnv.log(TAG, "转换后：" + meta.toString());
        return meta;
    }

    private AbstractValuesTester findTester(ValuePattern pattern){
        switch (pattern){
            case EqualsTo:
                EqualsToTester equalsToTester = new EqualsToTester();
                switch (pattern.valueType){
                    case Float:
                        equalsToTester.setFloatValue(Double.valueOf(pattern.value));
                        break;
                    case Int:
                        equalsToTester.setIntValue(Long.valueOf(pattern.value));
                        break;
                    case String:
                        equalsToTester.setStringValue(pattern.value);
                        break;
                }
                return equalsToTester;
            case MinLength:
                MinLengthTester minLengthTester = new MinLengthTester();
                minLengthTester.setIntValue(Long.parseLong(pattern.value));
                return minLengthTester;
            case MaxLength:
                MaxLengthTester maxLengthTester = new MaxLengthTester();
                maxLengthTester.setIntValue(Long.parseLong(pattern.value));
                return maxLengthTester;
            case MinValue:
                MinValueTester minValueTester = new MinValueTester();
                minValueTester.setFloatValue(Double.valueOf(pattern.value));
                return minValueTester;
            case MaxValue:
                MaxValueTester maxValueTester = new MaxValueTester();
                maxValueTester.setFloatValue(Double.valueOf(pattern.value));
                return maxValueTester;
            case RangeLength:
                RangeLengthTester rangeLengthTester = new RangeLengthTester();
                rangeLengthTester.setMinIntValue(Long.parseLong(pattern.minValue));
                rangeLengthTester.setMaxIntValue(Long.parseLong(pattern.maxValue));
                return rangeLengthTester;
            case RangeValue:
                RangeValueTester rangeValueTester = new RangeValueTester();
                rangeValueTester.setMinFloatValue(Double.valueOf(pattern.minValue));
                rangeValueTester.setMaxFloatValue(Double.valueOf(pattern.maxValue));
                return rangeValueTester;
            case Required:
                return new RequiredValueTester();
            default:
                return new RequiredValueTester(){
                    @Override
                    public boolean test(String content) {
                        return false;
                    }
                };
        }
    }

}
