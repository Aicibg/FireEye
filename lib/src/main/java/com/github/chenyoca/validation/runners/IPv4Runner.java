package com.github.chenyoca.validation.runners;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-26
 * IPv4
 */
public class IPv4Runner extends TestRunner{

    static final String IPV4_REGEX = "(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";

    public IPv4Runner(String message){
        super(message);
    }

    @Override
    public boolean test(String inputValue) {
        return isMatched(IPV4_REGEX, inputValue);
    }

}
