package com.github.chenyoca.validation.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chenyoca.validation.FireEye;
import com.github.chenyoca.validation.MessageDisplay;
import com.github.chenyoca.validation.Type;
import com.github.chenyoca.validation.supports.TextViewValuesLoader;

public class MainActivity extends Activity {

    FireEye fireEye;
    LinearLayout form;

    /**
     * 自定义显示出错消息的方式，默认是在 EditText 右边显示一个浮动提示框。
     */
    MessageDisplay messageDisplay = new MessageDisplay() {
        @Override
        public void dismiss(TextView field) {
            field.setError(null);
        }

        @Override
        public void show(TextView field, String message) {
            field.setError(message);
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        form = (LinearLayout) findViewById(R.id.form);

//      默认是在 EditText 右边显示一个浮动提示框。
//      final FireEye fireEye = new FireEye(form);

        // 指定自定义显示出错消息的方式，
        fireEye = new FireEye(form, messageDisplay);
        fireEye.add(R.id.form_field_1, Type.Required, Type.MobilePhone);
        fireEye.add(R.id.form_field_2, Type.CreditCard);
        fireEye.add(R.id.form_field_3, Type.Digits, Type.MaxLength.value(20));
        fireEye.add(R.id.form_field_4, Type.Email);
        fireEye.add(R.id.form_field_5, Type.EqualsTo.value(new TextViewValuesLoader(form, R.id.form_field_4)));
        fireEye.add(R.id.form_field_6, Type.Host);
        fireEye.add(R.id.form_field_7, Type.URL);
        fireEye.add(R.id.form_field_8, Type.MaxLength.value(5));
        fireEye.add(R.id.form_field_9, Type.MinLength.value(4));
        fireEye.add(R.id.form_field_10, Type.RangeLength.values(4, 8));
        fireEye.add(R.id.form_field_11, Type.NotBlank);
        fireEye.add(R.id.form_field_12, Type.Numeric);
        fireEye.add(R.id.form_field_13, Type.MaxValue.value(100));
        fireEye.add(R.id.form_field_14, Type.MinValue.value(20));
        fireEye.add(R.id.form_field_15, Type.RangeValue.values(18, 30));

        fireEye.debug(true);
        fireEye.applyInputType();

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = fireEye.test().passed ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                formCommit.setTextColor(getResources().getColor(color));
                formCommit.setTag(color);

            }
        });

    }
}
