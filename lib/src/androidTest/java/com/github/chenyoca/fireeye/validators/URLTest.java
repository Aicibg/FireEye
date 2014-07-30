package com.github.chenyoca.fireeye.validators;

import com.github.chenyoca.fireeye.Type;
import com.github.chenyoca.fireeye.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class URLTest extends GroupTester {

    @Override
    protected AbstractValidator setUpValidator() {
        return new HTTPURLValidator(Type.URL, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "http://www.baido.com",
                "http://www.baido.com:8088",
                "http://www.baido.com:8088/images/logo.png",
                "http://www.baido.com/logo.png",
                "https://www.baido.com",
                "http://stackoverflow.com/questions/123559/a-comprehensive-regex-for-mobilePhone-number-validation",
                "https://www.google.com.hk/search?newwindow=1&safe=strict&biw=1814&bih=963&q=mobilePhone+number+regex+pattern&oq=mobilePhone+regex+patt&gs_l=serp.3.1.0i19j0i8i30i19j0i5i30i19j0i8i30i19l2j0i5i30i19j0i8i30i19l2.2719540.2728868.0.2731221.16.15.0.1.1.0.213.1945.0j9j2.11.0...0.0...1c.1.9.serp.G5I1V2oogfc",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "ftp://www.baidoo.com/abc.png",
                "wget://target.com/a.file",
                "wss://abc.com",
                "ws://abc.com:8086/websocket",
                "XXXX",
                "a/xxxxxxx.exe",
                "thunder://ssssss",
        };
    }
}
