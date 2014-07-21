package com.github.chenyoca.validation.validators;

import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.AbstractValidator;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public class EmailTest extends GroupTester {

    @Override
    protected AbstractValidator validator() {
        return new EmailValidator(Type.Email, null);
    }

    @Override
    protected String[] thisShouldAllAssertTrue() {
        return new String[]{
                "chenyoca@gmail.com",
                "chen.yoca@gmail.com",
                "chen.yo-ca@gmail.com",
                "chen.yo-ca2013@gmail.com",
                "228441083@qq.com",
        };
    }

    @Override
    protected String[] thisShouldAllAssertFalse() {
        return new String[]{
                "a",
                "XXXX",
                "电子邮件@qq.com",
                "chenyoca#@gm@ail.com",
        };
    }
}
