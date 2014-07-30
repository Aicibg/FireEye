package com.github.chenyoca.fireeye.app;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-23
 */
@Config(emulateSdk = 16)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    final static int passedFlag = android.R.color.holo_green_dark;
    final static int failedFlag = android.R.color.holo_red_dark;

    @Test
    public void shouldAllPassed(){
        MainActivity activity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .get();

        Button button = (Button) activity.findViewById(R.id.form_commit);
        //verify.getCurrentTextColor()
        EditText  phone = (EditText) activity.form.findViewById(R.id.form_field_1);
        EditText  card = (EditText) activity.form.findViewById(R.id.form_field_2);
        EditText  digitMaxLength = (EditText) activity.form.findViewById(R.id.form_field_3);
        EditText  email = (EditText) activity.form.findViewById(R.id.form_field_4);
        EditText  equals = (EditText) activity.form.findViewById(R.id.form_field_5);
        EditText  host = (EditText) activity.form.findViewById(R.id.form_field_6);
        EditText  url = (EditText) activity.form.findViewById(R.id.form_field_8);
        EditText  maxLenght = (EditText) activity.form.findViewById(R.id.form_field_9);
        EditText  minLength = (EditText) activity.form.findViewById(R.id.form_field_10);
        EditText  rangeLength = (EditText) activity.form.findViewById(R.id.form_field_11);
        EditText  notBlank = (EditText) activity.form.findViewById(R.id.form_field_12);
        EditText  numeric = (EditText) activity.form.findViewById(R.id.form_field_13);
        EditText  maxVal = (EditText) activity.form.findViewById(R.id.form_field_7);
        EditText  minVal = (EditText) activity.form.findViewById(R.id.form_field_14);
        EditText  rangeVal = (EditText) activity.form.findViewById(R.id.form_field_15);

        checkFailed(button);

        phone.setText("123456");
        checkFailed(button);
        phone.setText("13800138000");
        checkPassed(button);

        card.setText("aa");
        checkFailed(button);
        card.setText("5205029546469280");
        checkPassed(button);

    }

    private void checkPassed(Button button){
        button.performClick();
        assertThat((Integer)button.getTag(), equalTo(passedFlag));
    }

    private void checkFailed(Button button){
        button.performClick();
        assertThat((Integer)button.getTag(), equalTo(failedFlag));
    }
}
