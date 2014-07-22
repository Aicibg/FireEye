package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.supports.AbstractValidator;

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
        validator.setIfNeedValues(setUpLongValues(), setUpStringValues(), setUpDoubleValues());
    }

    @Test
    public void shouldAllAssertTrue(){
        final String[] inputs = thisShouldAllAssertTrue();
        for (String i: inputs) assertTrue(validator.perform(i));
    }

    @Test
    public void shouldAllAssertFalse(){
        final String[] inputs = thisShouldAllAssertFalse();
        for (String i: inputs) assertFalse(validator.perform(i));
    }
}
