package com.github.yoojia.fireeye.testers;

/**
 * URL地址
 * @author Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-21
 * @since 2.0
 */
public class URLTester extends AbstractTester {

    static final String URL_REGEX =
            "^(https?:\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";

    @Override
    public boolean test(String content) {
        // 全部转换成小写来优化正则匹配性能
        final String url = content.toLowerCase();
        return testRegex(URL_REGEX, url);
    }
}
