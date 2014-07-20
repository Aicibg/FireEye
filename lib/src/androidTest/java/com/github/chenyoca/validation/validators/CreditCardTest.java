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
public class CreditCardTest {

    public CreditCardValidator validator;

    @Before public void setUp(){
        validator = new CreditCardValidator(Type.CreditCard,null);
    }

    @Test public void allFailed(){
        assertFalse(validator.perform("a"));
        assertFalse(validator.perform("1"));
        assertFalse(validator.perform("1!!!!!!!!!!!!!!!!!"));
        assertFalse(validator.perform("111111111111111"));
        assertFalse(validator.perform("11111111111111111111"));
        assertFalse(validator.perform("12345678901234567890"));
        assertFalse(validator.perform("12345678901234567890"));
    }

    @Test public void allPassed(){
        // Card Number from http://www.getcreditcardnumbers.com/

        // Mastercard
        assertTrue(validator.perform("5205029546469280"));
        assertTrue(validator.perform("5343976059451555"));
        assertTrue(validator.perform("5408895342589710"));
        assertTrue(validator.perform("5352581536929153"));
        assertTrue(validator.perform("5209432111085927"));

        // Visa
        assertTrue(validator.perform("4024007123265802"));
        assertTrue(validator.perform("4024007121199037"));
        assertTrue(validator.perform("4485505201465477"));
        assertTrue(validator.perform("4024007191017184"));
        assertTrue(validator.perform("4539078127027157"));

        // Discover
        assertTrue(validator.perform("6011312804633173"));
        assertTrue(validator.perform("6011097613803519"));
        assertTrue(validator.perform("6011020937135749"));
        assertTrue(validator.perform("6011839804335789"));
        assertTrue(validator.perform("6011451819584763"));

        // American Express
        assertTrue(validator.perform("379541867588903"));
        assertTrue(validator.perform("340875011721697"));
        assertTrue(validator.perform("372095540336906"));
        assertTrue(validator.perform("340237102737555"));
        assertTrue(validator.perform("344693098424770"));
    }
}
