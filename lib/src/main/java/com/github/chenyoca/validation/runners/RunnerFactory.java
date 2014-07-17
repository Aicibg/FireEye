package com.github.chenyoca.validation.runners;

import android.content.Context;
import android.content.res.Resources;

import com.github.chenyoca.validation.R;
import com.github.chenyoca.validation.Type;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class RunnerFactory {

    public static TestRunner build(Context c,Type type){
        Resources res = c.getResources();
        TestRunner runner;
        switch (type){
            case MobilePhone: runner = new MobilePhoneRunner(type, res.getString(R.string.validation_error_msg_mobile_phone)); break;
            case CreditCard: runner = new CreditCardRunner(type, res.getString(R.string.validation_error_msg_credit_card)); break;
            case Digits: runner = new DigitsRunner(type, res.getString(R.string.validation_error_msg_digits)); break;
            case Email: runner = new EmailRunner(type, res.getString(R.string.validation_error_msg_email)); break;
            case EqualsTo: runner = new EqualsToRunner(type, res.getString(R.string.validation_error_msg_equals)); break;
            case Host: runner = new HostRunner(type, res.getString(R.string.validation_error_msg_host)); break;
            case URL: runner = new HTTPURLRunner(type, res.getString(R.string.validation_error_msg_http_url)); break;
            case IPv4: runner = new IPv4Runner(type, res.getString(R.string.validation_error_msg_ipv4)); break;
            case MaxLength: runner = new MaxLengthRunner(type, res.getString(R.string.validation_error_msg_max_length)); break;
            case MinLength: runner = new MinLengthRunner(type, res.getString(R.string.validation_error_msg_min_length)); break;
            case RangeLength: runner = new RangeLengthRunner(type, res.getString(R.string.validation_error_msg_range_length)); break;
            case NotBlank: runner = new NotBlankRunner(type, res.getString(R.string.validation_error_msg_not_blank)); break;
            case Numeric: runner = new NumericRunner(type, res.getString(R.string.validation_error_msg_numeric)); break;
            case Required: runner = new RequiredRunner(type, res.getString(R.string.validation_error_msg_required)); break;
            case MaxValue: runner = new MaxValueRunner(type, res.getString(R.string.validation_error_msg_max_value)); break;
            case MinValue: runner = new MinValueRunner(type, res.getString(R.string.validation_error_msg_min_value)); break;
            case RangeValue: runner = new RangeValueRunner(type, res.getString(R.string.validation_error_msg_range_value)); break;
            default: runner = null; break;
        }
        return runner;
    }
}
