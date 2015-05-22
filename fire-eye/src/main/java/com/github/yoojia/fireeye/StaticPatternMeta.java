package com.github.yoojia.fireeye;

/**
 *
 * @author  Yoojia.Chen (yoojia.chen@gmail.com)
 * @version version 2015-05-22
 * @since   2.3
 */
class StaticPatternMeta extends PatternMeta<StaticPattern> {

    StaticPatternMeta(StaticPattern pattern, String message, int messageId) {
        super(pattern, message, messageId);
    }

    static StaticPatternMeta parse(StaticPattern pattern){
        return new StaticPatternMeta(pattern, pattern.message, pattern.messageId);
    }

    @Override
    public String toString() {
        return " {" +
                "pattern=" + pattern.name() +
                ", messageId=" + messageId +
                ", message='" + message + '\'' +
                '}';
    }

}
