package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * User: chenyoca@gmail.com
 * IsDate: 2014-06-26
 * HTTP URL
 */
class HTTPURLValidator extends AbstractValidator {

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    public HTTPURLValidator(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean isValid(String inputValue) {
        return isMatched(URL_REGEX, inputValue);
    }

}
