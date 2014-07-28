package com.github.chenyoca.validation;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.github.chenyoca.validation.supports.AbstractValidator;
import com.github.chenyoca.validation.validators.ValidatorFactory;

/**
 * User: YooJia.Chen@gmail.com
 * Date: 2014-06-25
 * Android Validator
 */
public class FormValidator {

    final static class SimpleMessageDisplay implements MessageDisplay{
        @Override
        public void dismiss(TextView field) { field.setError(null); }
        @Override
        public void show(TextView field, String message) { field.setError(message); }
    }

    private final Context context;
    private final MessageDisplay display;
    private final View form;
    private final SparseArray<_> validations = new SparseArray<_>();
    private final SparseArray<_> validationsEx = new SparseArray<_>();
    private final SparseArray<View> fields = new SparseArray<View>();
    private final SparseArray<String> values = new SparseArray<String>();

    boolean debug = false;

    private FormValidator(View form, Context context, MessageDisplay display){
        this.form = form;
        this.context = context;
        assert this.context != null;
        this.display = display;
        assert this.display != null;
    }

    public FormValidator(View form, MessageDisplay display){
        this(form, form.getContext(), display);
    }

    public FormValidator(View form){
        this(form,new SimpleMessageDisplay());
        assert form != null;
    }

    public FormValidator(Context context){
        this(null, context, new SimpleMessageDisplay());
    }

    /**
     * Add validate type to a view with view id.
     * @param viewId View ID
     * @param types Validate type
     * @return FormValidator instance.
     */
    public FormValidator add(int viewId, Type...types){
        add(null, viewId,types);
        return this;
    }

    /**
     * Add validate type to a view with special field
     * @param field special field
     * @param types Validate type
     * @return FormValidator instance.
     */
    public FormValidator add(TextView field, Type...types){
        int viewId = checkoutViewId(field);
        add(field, viewId,types);
        return this;
    }

    private void add(TextView field, int viewId, Type...types){
        if (types == null || types.length == 0){
            throw new IllegalArgumentException("Required 1 or more type to add !");
        }
        _ item = validations.get(viewId);
        if (item != null){
            for (Type t: types) item.add(context,t);
        }else{
            if (field != null){
                item = create(field, ValidatorFactory.build(context, types[0]));
            }else{
                item = create(viewId, ValidatorFactory.build(context, types[0]));
            }
            for (int i=1;i<types.length;i++) item.add(context,types[i]);
        }
    }

    /**
     * Add validate type to a view with special field
     * @param field special field
     * @param validators Test validators
     * @return FormValidator instance.
     */
    public FormValidator add(TextView field, AbstractValidator...validators){
        int viewId = checkoutViewId(field);
        add(null, viewId, validators);
        return this;
    }

    /**
     * Add validators to a view with view id.
     * @param viewId View ID
     * @param validators Test validators
     * @return FormValidator instance.
     */
    public FormValidator add(int viewId, AbstractValidator...validators){
        add(null, viewId, validators);
        return this;
    }

    protected void add(TextView field, int viewId, AbstractValidator...validators){
        if (validators == null || validators.length == 0){
            throw new IllegalArgumentException("Required 1 or more validator to add !");
        }
        _ item = validations.get(viewId);
        if (item != null){
            for (AbstractValidator v: validators) item.add(v);
        }else{
            if (field != null){
                item = create(field, validators[0]);
            }else{
                item = create(viewId, validators[0]);
            }
            for (int i=1;i<validators.length;i++) item.add(validators[i]);
        }
    }

    private _ create(int viewId, AbstractValidator validator){
        View field = form.findViewById(viewId);
        if ( ! (field instanceof TextView)){
            throw new IllegalArgumentException(
                    String.format("The view[ID=%d,Class=%s] IS NOT a TextView/EditText (OR TextView sub class ) View !",
                            viewId, field.getClass().getName()));
        }
        return create(viewId, (TextView)field, validator);
    }

    private _ create(TextView field, AbstractValidator validator){
        return create(checkoutViewId(field), field, validator);
    }

    private int checkoutViewId(TextView field){
        final int viewId = field.getId();
        if ( viewId == 0){
            throw new IllegalArgumentException(
                    String.format("The view[Class=%s] MUST has a valid View ID !",
                            field.getClass().getName()));
        }
        return viewId;
    }

    private _ create(int viewId, TextView field, AbstractValidator validator){
        _ item = new _(display, field , validator);
        validations.put(viewId, item);
        validationsEx.put(viewId, item);
        fields.put(viewId, field);
        values.put(viewId,"");
        return item;
    }

    /**
     * Apply InputType to views.
     * @param excludeViewIDs Apply exclude this view IDs
     * @return FormValidator instance.
     */
    public FormValidator applyInputType(int...excludeViewIDs){
        for (int exclude : excludeViewIDs){
            validationsEx.remove(exclude);
        }
        int size = validationsEx.size();
        for (int i=0;i<size;i++) validationsEx.valueAt(i).performInputType();
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
        final int size = validations.size();
        for (int i=0;i<size;i++) {
            r = validations.valueAt(i).performTest();
            if (debug) Log.i("Test","Field tested: "+r);
            passFlag &= r.passed;
            failedMsg = passFlag ? null : r.message;
            failedVal = r.value;
            values.setValueAt(i, r.value);
            if (!passFlag && !continuousTest) break;
        }
        return new TestResult(r != null && passFlag,failedMsg,failedVal);
    }

    /**
     * Get an extra value from field that WITHOUT isValid config by view id.
     * @param viewId View id WITHOUT isValid config
     * @return String value
     */
    public String getExtraValue(int viewId){
        return ((TextView)form.findViewById(viewId)).getText().toString();
    }

    /**
     * Get value from isValid form.
     * @param viewId View id
     * @return String value
     */
    public String getValue(int viewId){
        return values.get(viewId);
    }

    /**
     * Get the view Instance
     * @param viewId View ID
     * @param _ Type
     * @param <T> Type
     * @return View Instance
     */
    public <T> T getView(int viewId, Class<T> _){
        View field = fields.get(viewId);
        if (field != null){
            return (T)field;
        }else{
            return null;
        }
    }

    /**
     * Set debug
     * @param enable log debug info if true.
     */
    public void debug(boolean enable){
        debug = enable;
    }
}
