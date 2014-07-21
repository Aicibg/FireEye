package com.github.chenyoca.validation;

import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-21
 */
public abstract class AbstractTest {

    public abstract void shouldAllPassed();

    public abstract void shouldAllFailed();
}
