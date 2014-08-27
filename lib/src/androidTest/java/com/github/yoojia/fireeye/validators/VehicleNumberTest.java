package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class VehicleNumberTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new VehicleNumberValidator(Type.VehicleNumber, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "H80399",
                "A10086",
                "京A00008",
                "京A0001警",
                "京A0001挂",
                "海A-12345",
                "WJ18-12345",

        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "BBB",
                "H-123456",
                "AB10086",
                "北京A00008",
                "甲A#-12345",
                "WJ99-A12345",
        };
    }
}
