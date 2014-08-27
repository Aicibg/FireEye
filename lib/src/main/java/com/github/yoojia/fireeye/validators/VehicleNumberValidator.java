package com.github.yoojia.fireeye.validators;

import com.github.yoojia.fireeye.Type;
import com.github.yoojia.fireeye.supports.AbstractValidator;
import com.github.yoojia.fireeye.supports.VehicleNumberConst;

/**
 * Created by Yoojia.Chen
 * yoojia.chen@gmail.com
 * 2014-08-27
 * 中国车辆号牌校验
 */
class VehicleNumberValidator extends AbstractValidator{

    /**
     * 武警号牌特殊字符
     */
    static final String WJ_SPECIAL = "WJ[0-3]\\d";

    static final String REGEX = "^" +
            "["+
            VehicleNumberConst.AREA_CHARS +
            VehicleNumberConst.ARMY_CHARS+
            "]?" +
            "(" +
            "[A-Z]" +
            "|" +
            WJ_SPECIAL+
            ")-?" +
            "[" +
            "A-Z0-9" +
            VehicleNumberConst.CARD_TYPE+
            "]{5}$";

    protected VehicleNumberValidator(Type testType, String message) {
        super(testType, message);
    }

    @Override
    protected boolean isValid(String inputValue) {
        return isMatched(REGEX, inputValue);
    }
}
