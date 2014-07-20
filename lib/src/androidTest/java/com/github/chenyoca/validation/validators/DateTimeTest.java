package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-20
 */
@Config(emulateSdk = 16)
@RunWith(RobolectricTestRunner.class)
public class DateTimeTest {

    public DateTimeValidator dateValidator;
    public DateTimeValidator timeValidator;
    public DateTimeValidator dateTimeValidator;
    public DateTimeValidator futureValidator;
    public DateTimeValidator pastValidator;

    @Before public void setUp(){
        dateValidator = new DateTimeValidator(Type.IsDate,null);
        timeValidator = new DateTimeValidator(Type.IsTime,null);
        dateTimeValidator = new DateTimeValidator(Type.IsDateTime,null);
        futureValidator = new DateTimeValidator(Type.IsFuture,null);
        pastValidator = new DateTimeValidator(Type.IsPast,null);
    }

    @Test public void allFailed(){
        // Date
        assertFalse(dateValidator.perform("a"));
        assertFalse(dateValidator.perform("1"));
        assertFalse(dateValidator.perform("1222"));
        assertFalse(dateValidator.perform("abc"));
        assertFalse(dateValidator.perform("yyyy-MM-dd"));
        assertFalse(dateValidator.perform("2014-13-32"));
        assertFalse(dateValidator.perform("2014-02-30"));
        assertFalse(dateValidator.perform("0000-02-30"));
        assertFalse(dateValidator.perform("0000-00-00"));
        assertFalse(dateValidator.perform("2013-02-29"));
        dateValidator.setIfNeedValues(null,new String[]{"yyyy/MM/dd"}, null);
        assertFalse(dateValidator.perform("2014-07-21"));
        assertFalse(dateValidator.perform("2014-7-21"));
        dateValidator.setIfNeedValues(null,new String[]{"yyyy-MM-dd"}, null);
        assertFalse(dateValidator.perform("2014/07/21"));
        assertFalse(dateValidator.perform("2014/7/21"));

        // Time

        assertFalse(timeValidator.perform("a"));
        assertFalse(timeValidator.perform("00"));
        assertFalse(timeValidator.perform("2459"));
        assertFalse(timeValidator.perform("245900"));
        assertFalse(timeValidator.perform("hhmm"));
        assertFalse(timeValidator.perform("hhmmss"));
        assertFalse(timeValidator.perform("24:60"));
        assertFalse(timeValidator.perform("25:59"));
        assertFalse(timeValidator.perform("25:60"));
        assertFalse(timeValidator.perform("24:59:60"));
        timeValidator.setIfNeedValues(null,new String[]{"HH:mm:ss"}, null);
        assertFalse(timeValidator.perform("24-59-00"));
        assertFalse(timeValidator.perform("24:59-00"));

        // Date Time
        assertFalse(dateTimeValidator.perform("a"));
        assertFalse(dateTimeValidator.perform("yyyy-MM-dd HH:mm:ss"));
        assertFalse(dateTimeValidator.perform("2014-07-20+21:00:00"));
        assertFalse(dateTimeValidator.perform("2014-07-20 25:00:00"));
        assertFalse(dateTimeValidator.perform("2014-07-20 24:60:00"));
        assertFalse(dateTimeValidator.perform("2014-07-20 24:59:60"));
        dateTimeValidator.setIfNeedValues(null,new String[]{"YYYY/MM/DD HH:mm:ss"}, null);
        assertFalse(dateTimeValidator.perform("2014-07-20 21:00:00"));
        assertFalse(dateTimeValidator.perform("2014/07/20 21/00/00"));
        assertFalse(dateTimeValidator.perform("2014/07/20 21/00/61"));
        assertFalse(dateTimeValidator.perform("2014/07/20 21:00:61"));
    }

//    @Test
    public void allPassed(){
        // Date
        assertTrue(dateValidator.perform("2014-07-21"));
        assertTrue(dateValidator.perform("2014-12-31"));
        assertTrue(dateValidator.perform("2000-02-29"));

        dateValidator.setIfNeedValues(null,new String[]{"yyyy/MM/dd"}, null);
        assertTrue(dateValidator.perform("2014/07/21"));
        assertTrue(dateValidator.perform("2014/7/21"));
        dateValidator.setIfNeedValues(null,new String[]{"yyyy#MM#dd"}, null);
        assertTrue(dateValidator.perform("2014#07#21"));
        assertTrue(dateValidator.perform("2014#7#21"));
    }
}
