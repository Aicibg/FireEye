package com.github.yoojia.fireeye;

import android.view.View;
import android.widget.TextView;

/**
 * 表格查找器
 *
 * @author Yoojia.Chen (yoojia.chen@
 * @version version 2015-05-21
 * @since app.version
 */
public class Form {

    private final View form;

    public Form(View form) {
        this.form = form;
    }

    public <T extends TextView> T byId(int viewId){
        return (T) form.findViewById(viewId);
    }
}
