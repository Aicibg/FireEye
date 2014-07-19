package com.github.chenyoca.validation.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.chenyoca.validation.FormValidator;
import com.github.chenyoca.validation.MessageDisplay;
import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.EditTextLazyLoader;

public class MainActivity extends Activity {

    /**
     * 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框。
     */
    MessageDisplay messageDisplay = new MessageDisplay() {
        @Override
        public void dismiss(EditText field) {
            field.setError(null);
        }

        @Override
        public void show(EditText field, String message) {
            field.setError(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout form = (LinearLayout) findViewById(R.id.form);

//      默认是在 EditText 右边显示一个浮动提示框。
//      final FormValidator av = new FormValidator(form);

//      指定自定义显示出错消息的方式，
        final FormValidator av = new FormValidator(form, messageDisplay);
        av.add(R.id.form_field_1, Type.Required, Type.MobilePhone);
        av.add(R.id.form_field_2, Type.CreditCard);
        av.add(R.id.form_field_3, Type.Digits, Type.MaxLength.value(20));
        av.add(R.id.form_field_4, Type.Email);
        av.add(R.id.form_field_5, Type.EqualsTo.lazy(new EditTextLazyLoader(form,R.id.form_field_4)));
        av.add(R.id.form_field_6, Type.Host);
        av.add(R.id.form_field_7, Type.URL);
        av.add(R.id.form_field_8, Type.MaxLength.value(5));
        av.add(R.id.form_field_9, Type.MinLength.value(4));
        av.add(R.id.form_field_10, Type.RangeLength.values(4,8));
        av.add(R.id.form_field_11, Type.NotBlank);
        av.add(R.id.form_field_12, Type.Numeric);
        av.add(R.id.form_field_13, Type.MaxValue.value(100));
        av.add(R.id.form_field_14, Type.MinValue.value(20));
        av.add(R.id.form_field_15, Type.RangeValue.values(18,30));

        av.debug(true);
        av.applyInputType();

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = av.test().passed ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                formCommit.setTextColor(getResources().getColor(color));

            }
        });

    }
}
