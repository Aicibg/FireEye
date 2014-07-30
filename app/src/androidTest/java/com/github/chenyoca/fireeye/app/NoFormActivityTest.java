package com.github.chenyoca.fireeye.app;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-28
 */
@Config(emulateSdk = 16)
@RunWith(RobolectricTestRunner.class)
public class NoFormActivityTest {

    final static int passedFlag = android.R.color.holo_green_dark;
    final static int failedFlag = android.R.color.holo_red_dark;

    @Test
    public void shouldAllPassed(){
        NoFormActivity activity = Robolectric.buildActivity(NoFormActivity.class)
                .create()
                .get();

        Button button = (Button) activity.findViewById(R.id.form_commit);
        //verify.getCurrentTextColor()
        EditText  phone = (EditText) activity.findViewById(R.id.form_field_1);
        EditText  card = (EditText) activity.findViewById(R.id.form_field_2);


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
