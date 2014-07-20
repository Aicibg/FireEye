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
public class EmailTest {

    public EmailValidator validator;

    @Before public void setUp(){
        validator = new EmailValidator(Type.Email,null);
    }

    @Test public void allFailed(){
        assertFalse(validator.perform("a"));
        assertFalse(validator.perform("XXXX"));
        assertFalse(validator.perform("电子邮件@qq.com"));
        assertFalse(validator.perform("chenyoca#@gm@ail.com"));
    }

    @Test public void allPassed(){
        assertTrue(validator.perform("chenyoca@gmail.com"));
        assertTrue(validator.perform("chen.yoca@gmail.com"));
        assertTrue(validator.perform("chen.yo-ca@gmail.com"));
        assertTrue(validator.perform("chen.yo-ca2013@gmail.com"));
        assertTrue(validator.perform("228441083@qq.com"));

    }
}
