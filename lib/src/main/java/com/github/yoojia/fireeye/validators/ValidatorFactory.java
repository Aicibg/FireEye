package com.github.yoojia.fireeye.validators;

import android.content.Context;
import android.content.res.Resources;

import com.github.yoojia.fireeye.R;
import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-15
 */
public class ValidatorFactory {

    public static AbstractValidator build(Context c,Type type){
        Resources res = c.getResources();
        AbstractValidator validator;
        switch (type){
            case MobilePhone: validator = new MobilePhoneValidator(type, res.getString(R.string.validation_error_msg_mobile_phone)); break;
            case CreditCard: validator = new CreditCardValidator(type, res.getString(R.string.validation_error_msg_credit_card)); break;
            case Digits: validator = new DigitsValidator(type, res.getString(R.string.validation_error_msg_digits)); break;
            case Email: validator = new EmailValidator(type, res.getString(R.string.validation_error_msg_email)); break;
            case EqualsTo: validator = new EqualsToValidator(type, res.getString(R.string.validation_error_msg_equals)); break;
            case IsDate:validator = new DateTimeValidator(type, res.getString(R.string.validation_error_msg_is_date)); break;
            case IsTime:validator = new DateTimeValidator(type, res.getString(R.string.validation_error_msg_is_time)); break;
            case IsDateTime:validator = new DateTimeValidator(type, res.getString(R.string.validation_error_msg_is_date_time)); break;
            case IsFuture:validator = new DateTimeValidator(type, res.getString(R.string.validation_error_msg_is_future)); break;
            case IsPast: validator = new DateTimeValidator(type, res.getString(R.string.validation_error_msg_is_past)); break;
            case Host: validator = new HostValidator(type, res.getString(R.string.validation_error_msg_host)); break;
            case URL: validator = new HTTPURLValidator(type, res.getString(R.string.validation_error_msg_http_url)); break;
            case IPv4: validator = new IPv4Validator(type, res.getString(R.string.validation_error_msg_ipv4)); break;
            case MaxLength: validator = new MaxLengthValidator(type, res.getString(R.string.validation_error_msg_max_length)); break;
            case MinLength: validator = new MinLengthValidator(type, res.getString(R.string.validation_error_msg_min_length)); break;
            case RangeLength: validator = new RangeLengthValidator(type, res.getString(R.string.validation_error_msg_range_length)); break;
            case NotBlank: validator = new NotBlankValidator(type, res.getString(R.string.validation_error_msg_not_blank)); break;
            case Numeric: validator = new NumericValidator(type, res.getString(R.string.validation_error_msg_numeric)); break;
            case Required: validator = new RequiredValidator(type, res.getString(R.string.validation_error_msg_required)); break;
            case MaxValue: validator = new MaxValueValidator(type, res.getString(R.string.validation_error_msg_max_value)); break;
            case MinValue: validator = new MinValueValidator(type, res.getString(R.string.validation_error_msg_min_value)); break;
            case RangeValue: validator = new RangeValueValidator(type, res.getString(R.string.validation_error_msg_range_value)); break;
            case VehicleNumber: validator = new VehicleNumberValidator(type, res.getString(R.string.validation_error_msg_vehicle)); break;
            default: validator = null; break;
        }
        return validator;
    }
}
