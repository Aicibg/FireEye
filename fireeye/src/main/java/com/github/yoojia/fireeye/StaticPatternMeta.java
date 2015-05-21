package com.github.yoojia.fireeye;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.github.yoojia.fireeye.testers.AbstractTester;
import com.github.yoojia.fireeye.testers.BankCardTester;
import com.github.yoojia.fireeye.testers.DigitsTester;
import com.github.yoojia.fireeye.testers.EmailTester;
import com.github.yoojia.fireeye.testers.HostTester;
import com.github.yoojia.fireeye.testers.IDCardTester;
import com.github.yoojia.fireeye.testers.IPv4Tester;
import com.github.yoojia.fireeye.testers.MobileTester;
import com.github.yoojia.fireeye.testers.NotBlankTester;
import com.github.yoojia.fireeye.testers.NumericTester;
import com.github.yoojia.fireeye.testers.RequiredTester;
import com.github.yoojia.fireeye.testers.URLTester;
import com.github.yoojia.fireeye.testers.VehicleNumberTester;

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
final class StaticPatternMeta extends PatternMeta<StaticPattern>{

    StaticPatternMeta(int viewId, TextView field, StaticPattern[] patterns) {
        super(viewId, field);
        addPatterns(patterns);
    }

    @Override
    public Result performTest(){
        final String value = input.getText().toString();
        // 当输入内容为空时，如果第一个校验模式不是“Required”，则跳过后面的配置。
        StaticPattern first = patterns.get(0);
        if (TextUtils.isEmpty(value) && !StaticPattern.Required.equals(first)){
            return Result.passed(null);
        }
        for (StaticPattern pattern : patterns){
            AbstractTester tester = findTester(pattern);
            final boolean passed = tester.performTest(value);
            if (!passed){
                if (FireEyeEnv.isDebug){
                    Log.d("S-Meta", "Result > passed: NO, value: " + value + ", message: " + pattern.getMessage());
                }
                return Result.reject(pattern.getMessage(), value);
            }
        }
        if (FireEyeEnv.isDebug){
            Log.d("S-Meta", "Result > passed: NO, value: " + value);
        }
        return Result.passed(value);
    }

    private AbstractTester findTester(StaticPattern pattern){
        switch (pattern){
            case BankCard: return new BankCardTester();
            case Digits: return new DigitsTester();
            case Email: return new EmailTester();
            case Host: return new HostTester();
            case IDCard: return new IDCardTester();
            case IPv4: return new IPv4Tester();
            case Mobile: return new MobileTester();
            case NotBlank: return new NotBlankTester();
            case Numeric: return new NumericTester();
            case Required: return new RequiredTester();
            case URL: return new URLTester();
            case VehicleNumber: return new VehicleNumberTester();
            default: return new AbstractTester() {
                @Override
                public boolean test(String content) {
                    return false;
                }
            };
        }
    }
}
