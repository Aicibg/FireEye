package com.github.yoojia.fireeye.validators;

import android.text.TextUtils;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-20
 */
class DateTimeValidator extends AbstractValidator {

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
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                        break;
                    case IsTime:
                        sdf = new SimpleDateFormat("HH:mm:ss");
                        break;
                    case IsDateTime:
                    case IsFuture:
                    case IsPast:
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
                System.out.println("[-] DateTime before 1970-01-01 GMT !!!");
            }

            // Time base: extraString[1]
            final Date base = extraString[1] == null ? new Date() : sdf.parse(extraString[1]);
            boolean result = false;
            switch (testType){
                case IsFuture:
                    result = base.before(date);//now.getTime() < date.getTime();
                    break;
                case IsPast:
                    result = base.after(date);//now.getTime() > date.getTime();
                    break;
                default:
                    result = true;
            }
            if (debug){
                System.out.println("[>] Date time base[S]: " + extraString[1]);
                System.out.println("[>] Date time base[T]: " + base.getTime());
                System.out.println("[>] Date time value[S]: " + inputValue);
                System.out.println("[>] Date time value[T]: " + date.getTime());
                System.out.println("[>] result: " + result);
            }
            return result;
        }catch (Exception e){
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
