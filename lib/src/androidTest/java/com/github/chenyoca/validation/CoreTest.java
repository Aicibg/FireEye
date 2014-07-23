package com.github.chenyoca.validation;

import android.content.Context;
import android.widget.EditText;

import com.github.chenyoca.validation.validators.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-23
 */
@Config(emulateSdk = 16)
@RunWith(RobolectricTestRunner.class)
public class CoreTest {

    MessageDisplay display = new MessageDisplay() {
        @Override
        public void dismiss(EditText field) { }

        @Override
        public void show(EditText field, String message) { }
    };

    Context context = Robolectric.getShadowApplication().getApplicationContext();

    @Test
    public void shouldReadText(){

        EditText req = new EditText(context);
        _ item = new _(display, req, ValidatorFactory.build(context, Type.Required));
        req.setText("A");
        assertTrue(item.performTest().passed);
        req.setText("#");
        assertTrue(item.performTest().passed);
        req.setText("1");
        assertTrue(item.performTest().passed);
        req.setText("*****2");
        assertTrue(item.performTest().passed);
        req.setText("3.000111....");
        assertTrue(item.performTest().passed);

        req.setText("");
        assertFalse(item.performTest().passed);

    }

    @Test
    public void shouldReadValue(){
        EditText value = new EditText(context);
        _ item = new _(display, value, ValidatorFactory.build(context, Type.Required));
        item.add(context, Type.MinValue.value(10));

        assertFalse(item.performTest().passed);

        value.setText("10");
        assertTrue(item.performTest().passed);
        value.setText("10.5");
        assertTrue(item.performTest().passed);
        value.setText("11");
        assertTrue(item.performTest().passed);
        value.setText("55");
        assertTrue(item.performTest().passed);
        value.setText("9999");
        assertTrue(item.performTest().passed);

        value.setText("9.99");
        assertFalse(item.performTest().passed);

        value.setText("9.99999999");
        assertFalse(item.performTest().passed);

        value.setText("1");
        assertFalse(item.performTest().passed);
    }
}
