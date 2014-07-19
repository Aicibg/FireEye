package com.github.chenyoca.validation;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
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
        EditText editText = (EditText)field;
        item = new _(display, editText , type);
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

    public TestResult test(){
        return test(true);
    }

    public TestResult test(boolean continuousTest){
        boolean passFlag = true;
        String failedMsg = "NO_TEST_CONFIGURATIONS";
        String failedVal = null;
        TestResult r = null;
        int size = configs.size();
        for (int i=0;i<size;i++) {
            r = configs.valueAt(i).performTest();
            if (debug) Log.i("Test","Tested result: "+r);
            passFlag &= r.passed;
            failedMsg = passFlag ? null : r.message;
            failedVal = r.value;
            if (!passFlag && !continuousTest) break;
        }
        return new TestResult(r != null && passFlag,failedMsg,failedVal);
    }

    boolean debug = false;
    public void debug(boolean enable){
        debug = enable;
    }
}
