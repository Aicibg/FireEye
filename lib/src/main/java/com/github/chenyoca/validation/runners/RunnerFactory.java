package com.github.chenyoca.validation.runners;

import android.content.Context;
import android.content.res.Resources;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.Types;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class RunnerFactory {

    public static TestRunner build(Context c,Types type){
        Resources res = c.getResources();
        TestRunner runner;
        switch (type){
            case MobilePhone: runner = new MobilePhoneRunner(res.getString(R.string.validation_error_msg_mobile_phone)); break;
            case CreditCard: runner = new CreditCardRunner(res.getString(R.string.validation_error_msg_credit_card)); break;
            case Digits: runner = new DigitsRunner(res.getString(R.string.validation_error_msg_digits)); break;
            case Email: runner = new EmailRunner(res.getString(R.string.validation_error_msg_email)); break;
            case EqualsTo: runner = new EqualsToRunner(res.getString(R.string.validation_error_msg_equals)); break;
            case Host: runner = new HostRunner(res.getString(R.string.validation_error_msg_host)); break;
            case URL: runner = new HTTPURLRunner(res.getString(R.string.validation_error_msg_http_url)); break;
            case IPv4: runner = new IPv4Runner(res.getString(R.string.validation_error_msg_ipv4)); break;
            case MaxLength: runner = new MaxLengthRunner(res.getString(R.string.validation_error_msg_max_length)); break;
            case MinLength: runner = new MinLengthRunner(res.getString(R.string.validation_error_msg_min_length)); break;
            case RangeLength: runner = new RangeLengthRunner(res.getString(R.string.validation_error_msg_range_length)); break;
            case NotBlank: runner = new NotBlankRunner(res.getString(R.string.validation_error_msg_not_blank)); break;
            case Numeric: runner = new NumericRunner(res.getString(R.string.validation_error_msg_numeric)); break;
            case Required: runner = new RequiredRunner(res.getString(R.string.validation_error_msg_required)); break;
            case MaxValue: runner = new MaxValueRunner(res.getString(R.string.validation_error_msg_max_value)); break;
            case MinValue: runner = new MinValueRunner(res.getString(R.string.validation_error_msg_min_value)); break;
            case RangeValue: runner = new RangeValueRunner(res.getString(R.string.validation_error_msg_range_value)); break;
            default: runner = null; break;
        }
        return runner;
    }
}
