package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;

import org.junit.Assert;
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
public class DigitsTest {

    public DigitsValidator validator;

    @Before public void setUp(){
        validator = new DigitsValidator(Type.Digits,null);
    }

    @Test public void allFailed(){
        Assert.assertTrue(validator.perform("aaa123"));
        Assert.assertTrue(validator.perform("###"));
        Assert.assertTrue(validator.perform("123343434"));
        Assert.assertTrue(validator.perform("abc"));
        Assert.assertTrue(validator.perform("/**//%%"));
        Assert.assertTrue(validator.perform("0123"));
        Assert.assertTrue(validator.perform("0x0123"));
        Assert.assertTrue(validator.perform("0xFFFF"));
    }

    @Test public void allPassed(){
        Assert.assertTrue(validator.perform("123"));
        Assert.assertTrue(validator.perform("13800138000L"));
        Assert.assertTrue(validator.perform("123343434"));
        Assert.assertTrue(validator.perform(""+Integer.MAX_VALUE));
        Assert.assertTrue(validator.perform("0123456789"));
        Assert.assertTrue(validator.perform("13800138000"));
        Assert.assertTrue(validator.perform("00000"));

    }
}
