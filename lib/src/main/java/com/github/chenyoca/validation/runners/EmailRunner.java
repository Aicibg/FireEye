package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * Email
 */
public class EmailRunner extends TestRunner{

    static final String EMAIL_REGEX =
            "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public EmailRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return isMatched(EMAIL_REGEX, inputValue);
    }

}
