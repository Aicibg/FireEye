package com.github.yoojia.fireeye.validators;

import android.text.TextUtils;

import com.github.yoojia.fireeye.supports.AbstractValidator;

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
 * 2014-07-21
 */
@Config(emulateSdk = 16)
@RunWith(RobolectricTestRunner.class)
public abstract class GroupTester {

    protected abstract AbstractValidator setUpValidator();

    protected abstract String[] thisShouldAllAssertTrue();
    protected abstract String[] thisShouldAllAssertFalse();

    protected String[] setUpStringValues(){ return null;}
    protected long[] setUpLongValues(){ return null;}
    protected double[] setUpDoubleValues(){ return null;}

    AbstractValidator validator;

    @Before
    public void setUp(){
        validator = setUpValidator();
        validator.setValues(setUpLongValues(), setUpStringValues(), setUpDoubleValues());
    }

    @Test
    public void shouldAllAssertTrue(){
        final String[] inputs = thisShouldAllAssertTrue();
        for (String i: inputs) {
            boolean flag = validator.perform(i);
            assertTrue(">> Assert TRUE, but false, error: " + validator.getError(),flag);
        }
    }

    @Test
    public void shouldAllAssertFalse(){
        final String[] inputs = thisShouldAllAssertFalse();
        for (String i: inputs) {
            boolean flag = validator.perform(i);
            assertFalse(">> Assert FALSE, but true, error: " + validator.getError(), flag);

        }
    }
}
