package com.github.chenyoca.validation.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.chenyoca.validation.FormValidator;
import com.github.chenyoca.validation.Type;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-28
 */
public class NoFormActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FormValidator validator = new FormValidator(this);
        EditText mobile = (EditText) findViewById(R.id.form_field_1);
        EditText card = (EditText) findViewById(R.id.form_field_2);
        validator.add(mobile, Type.Required, Type.MobilePhone);
        validator.add(card, Type.CreditCard);
        validator.debug(true);
        validator.applyInputType();

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = validator.test().passed ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                formCommit.setTextColor(getResources().getColor(color));
                formCommit.setTag(color);

            }
        });
    }
}
