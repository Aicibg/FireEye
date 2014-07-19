package com.github.chenyoca.validation.runners;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.TestRunner;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * HTTP URL
 */
class HTTPURLRunner extends TestRunner {

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    public HTTPURLRunner(Type testType, String message){
        super(testType, message);
    }

    @Override
    public boolean test(String inputValue) {
        return isMatched(URL_REGEX, inputValue);
    }

}
