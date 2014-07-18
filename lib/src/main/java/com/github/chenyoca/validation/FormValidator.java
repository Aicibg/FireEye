package com.github.chenyoca.validation;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

/**
 * User: YooJia.Chen@gmail.com
 * Date: 2014-06-25
 * Android Validator
 */
public class FormValidator {

    final Context context;
    final MessageDisplay display;
    final View form;
    final SparseArray<_> configs = new SparseArray<_>();
    final SparseArray<_> weakHold = new SparseArray<_>();

    public FormValidator(View form, MessageDisplay display){
        this.form = form;
        assert form != null;
        this.context = form.getContext();
        this.display = display;
    }

    public FormValidator(View form){
        this(form,new MessageDisplay() {
            @Override
            public void dismiss(EditText field) {
                field.setError(null);
            }

            @Override
            public void show(EditText field, String message) {
                field.setError(message);
            }
        });
    }

    public FormValidator add(int viewId, Type...types){
        for (Type t : types) add(viewId, t);
        return this;
    }

    private void add(int viewId, Type type){
        // If config(key by view id) exists, just add.
        _ item = configs.get(viewId);
        if (item != null){
            item.add(context, type);
            return;
        }
        // NO, create it.
        View field = form.findViewById(viewId);
        if ( ! (field instanceof EditText)){
            throw new IllegalArgumentException(
                    String.format("View(id=%d) IS NOT A EditText View !", viewId));
        }
        item = new _(display, (EditText)field, type);
        configs.put(viewId, item);
        weakHold.put(viewId, item);
    }

    public FormValidator applyInputType(int...excludeViewIDs){
        for (int exclude : excludeViewIDs){
            weakHold.remove(exclude);
        }
        int size = weakHold.size();
        for (int i=0;i<size;i++) weakHold.valueAt(i).performInputType();
        return this;
    }

    public ResultWrapper test(){
        return test(true);
    }

    public ResultWrapper test(boolean continuous){
        String message = null;
        boolean passed = true;
        String value = null;
        int size = configs.size();
        for (int i=0;i<size;i++) {
            ResultWrapper r = configs.valueAt(i).performTest();
            passed &= r.passed;
            message = r.message;
            value = r.value;
            if (!continuous && !r.passed)break;
        }
        return new ResultWrapper(passed, message, value);
    }

}
