package com.github.yoojia.fireeye.testers;

/**
 *
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class VehicleNumberTester extends AbstractTester {

    static final String VEHICLE_REGEX = "^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川贵云藏陕甘青宁新渝]?[A-Z][A-HJ-NP-Z0-9学挂港澳练]{5}$";

    @Override
    public boolean test(String content) {
        return testRegex(VEHICLE_REGEX, content);
    }
}
