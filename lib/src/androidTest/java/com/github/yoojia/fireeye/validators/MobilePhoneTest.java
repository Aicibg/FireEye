package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-22
 */
public class MobilePhoneTest extends GroupTester {
    @Override
    protected AbstractValidator setUpValidator() {
        return new MobilePhoneValidator(Type.Mobile, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "13800138000",
                "8613800138000",
                "+8613800138000",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "1",
                "1234567890123",
        };
    }
}
