package com.github.chenyoca.fireeye.validators;

import android.text.TextUtils;
import android.util.Log;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

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
    protected boolean isValid(String inputValue) {
        try{
            final DateFormat sdf;
            // Format : extraString[0]
            if (!TextUtils.isEmpty(extraString[0])){
                sdf = new SimpleDateFormat(extraString[0]);
            }else{
                switch (testType){
                    case IsDate:
                        sdf = SimpleDateFormat.getDateInstance();
                        break;
                    case IsTime:
                        sdf = SimpleDateFormat.getTimeInstance();
                        break;
                    case IsDateTime:
                    case IsFuture:
                    case IsPast:
                        sdf = SimpleDateFormat.getDateTimeInstance();
                        break;
                    default:
                        sdf = null;
                        break;
                }
            }
            if(sdf == null) return false;
            sdf.setLenient(false);
            final Date date = sdf.parse(inputValue);
            if (date.getTime() < 0){
                Log.w("DateTime","[-] DateTime before Jan. 1, 1970 GMT !!!");
            }
            // Time base: extraString[1]
            final Date base = extraString[1] == null ? new Date() : sdf.parse(extraString[1]);
            switch (testType){
                case IsFuture:
                    return base.before(date);//now.getTime() < date.getTime();
                case IsPast:
                    return base.after(date);//now.getTime() > date.getTime();
                default:
                    return true;
            }
        }catch (ParseException e){
            e.printStackTrace();
            error = e.getMessage();
            return false;
        }
    }

    @Override
    public void verifyValues() {
        if (!ExtraType.None.equals(extraType) && !ExtraType.String.equals(extraType))
            throw new IllegalArgumentException(getClass().getSimpleName() +
                    " ONLY accept String values." +
                    " Set by " +
                    "'Type.TYPE.value(...)' / " +
                    "'Type.TYPE.values(...)' / " +
                    "'Type.Type.format(...)'.");
    }
}
