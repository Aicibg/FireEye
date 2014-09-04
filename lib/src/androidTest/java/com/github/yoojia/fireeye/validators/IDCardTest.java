package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-20
 */
public class IDCardTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new IDCardValidator(Type.IDCard,null);
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "1",
                "11111111",
                "123456789012345678",
                "50024119750611322A",
                "50024119750611322x",
                "50024119750611322X",
        };
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "500241197506113220",
                "621126197903241970",
                "331123198809285121",
                "15098119810906972X",
                "360600199007272814",
        };
    }

}
