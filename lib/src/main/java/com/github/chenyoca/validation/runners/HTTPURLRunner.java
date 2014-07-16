package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * HTTP URL
 */
public class HTTPURLRunner extends TestRunner{

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    public HTTPURLRunner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        return isMatched(URL_REGEX, inputValue);
    }

}
