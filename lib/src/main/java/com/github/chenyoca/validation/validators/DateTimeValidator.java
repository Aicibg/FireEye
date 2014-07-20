package com.github.chenyoca.validation.validators;

import android.text.TextUtils;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-20
 */
public class DateTimeValidator extends AbstractValidator {

    protected DateTimeValidator(Type testType, String message) {
        super(testType, message);
    }

    @Override
    protected boolean test(String inputValue) {
        try{
            DateFormat sdf;
            if (!TextUtils.isEmpty(extraString[0])){
                sdf = new SimpleDateFormat(extraString[0]);
            }else{
                switch (testType){
                    case IsDate:
                        sdf = SimpleDateFormat.getTimeInstance();
                        break;
                    case IsTime:
                        sdf = SimpleDateFormat.getTimeInstance();
                        break;
                    case IsDateTime:
                        sdf = SimpleDateFormat.getDateTimeInstance();
                        break;
                    default:
                        sdf = null;
                        break;
                }
            }
            return sdf != null && testDate(sdf.parse(inputValue));
        }catch (ParseException e){
            return false;
        }
    }

    boolean testDate(Date date){
        Date now = new Date();
        switch (testType){
            case IsFuture:
                return now.after(date);
            case IsPast:
                return now.before(date);
            default:
                return true;
        }
    }

    @Override
    public void onAdded() {
        if (!ExtraType.None.equals(extraType) && !ExtraType.String.equals(extraType))
            throw new IllegalArgumentException(getClass().getSimpleName() +
                    " ONLY accept String values." +
                    " Set by " +
                    "'Type.TYPE.value(...)' / " +
                    "'Type.TYPE.values(...)' / " +
                    "'Type.Type.format(...)'.");
    }
}
