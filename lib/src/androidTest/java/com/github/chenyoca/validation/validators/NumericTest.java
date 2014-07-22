package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class NumericTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new NumericValidator(Type.Numeric, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "-10",
                "16",
                "+99",
                "-16",
                "-0",
                ""+Long.MAX_VALUE,
                "123456789012L",
                "0xFF",
                "0xABDECF",
                "0xFF",
                "0xabcdef",
                "0x89FF",
                "8e5",
                "3.14159f",
                "3.14159d",
                "3.14159",
                "+10",
                "0144",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "99,99",
                "#abde",
                "1.2.3",
                "1.2zzz",
                "0xzy123",
                "",
                "    123",
        };
    }
}
