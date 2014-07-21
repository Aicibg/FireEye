package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.AbstractTest;
import com.github.chenyoca.validation.Type;

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
public class MaxValueTest extends AbstractTest{

    MaxValueValidator validator = new MaxValueValidator(Type.MaxValue.value(20), null);

    @Test
    @Override
    public void shouldAllPassed() {
        validator.onAdded();
        assertTrue(validator.perform("-30"));
        assertTrue(validator.perform("-20"));
        assertTrue(validator.perform("-19"));
        assertTrue(validator.perform("-1"));
        assertTrue(validator.perform("0"));
        assertTrue(validator.perform("5"));
        assertTrue(validator.perform("10"));
        assertTrue(validator.perform("19"));
        assertTrue(validator.perform("20"));
    }

    @Test
    @Override
    public void shouldAllFailed() {
        validator.onAdded();
        assertFalse(validator.perform("20.1"));
        assertFalse(validator.perform("20.2"));
        assertFalse(validator.perform("21"));
        assertFalse(validator.perform("29"));
        assertFalse(validator.perform("29999"));
    }
}
