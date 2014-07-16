package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Chinese mobile phone
 */
public class MobilePhoneRunner extends TestRunner{

    static final String PHONE_REGEX = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";

    public MobilePhoneRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        return isMatched(PHONE_REGEX, inputValue);
    }

}
