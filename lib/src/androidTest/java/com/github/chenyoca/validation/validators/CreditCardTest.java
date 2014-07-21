package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-20
 */
public class CreditCardTest extends GroupTester {

    @Override
    protected AbstractValidator validator() {
        return new CreditCardValidator(Type.CreditCard,null);
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "1",
                "11111111",
                "1234567890123456",
                "5205029546469111",
                "1115029546469280",
        };
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "5205029546469280",
                "5343976059451555",
                "5408895342589710",
                "5352581536929153",
                "5209432111085927",
                "4024007123265802",
                "4024007121199037",
                "4485505201465477",
                "4024007191017184",
                "4539078127027157",
                "6011312804633173",
                "6011097613803519",
                "6011020937135749",
                "6011839804335789",
                "6011451819584763",
                "379541867588903",
                "340875011721697",
                "372095540336906",
                "340237102737555",
                "344693098424770",
        };
    }

}
