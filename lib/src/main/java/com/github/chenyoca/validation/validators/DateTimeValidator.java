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
    protected boolean isValid(String inputValue) {
        try{
            final DateFormat sdf;
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
            if(sdf != null){
                sdf.setLenient(false);
                return testDate(sdf.parse(inputValue));
            }else{
                return false;
            }
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }

    boolean testDate(Date date){
        final Date now = new Date();
        switch (testType){
            case IsFuture:
                return now.before(date);//now.getTime() < date.getTime();
            case IsPast:
                return now.after(date);//now.getTime() > date.getTime();
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
