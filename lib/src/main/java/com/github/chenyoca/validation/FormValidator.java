package com.github.chenyoca.validation;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.github.chenyoca.validation.runners.TestRunner;

/**
 * User: YooJia.Chen@gmail.com
 * Date: 2014-06-25
 * Android Validator
 */
public class FormValidator {

    private MessageDisplay display;
    private ViewGroup form;

    private final Context context;

    // Values of fields
    private SparseArray<String> valuesOfFields = new SparseArray<String>();

    // Configs of the form
    private SparseArray<Config> formConfigArray = new SparseArray<Config>();

    public FormValidator(Context context){
        this(context, new MessageDisplay() {
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

    public FormValidator(Context context, MessageDisplay display){
        this.display = display;
        this.context = context;
    }

    public static void enableDebug(){
        _.DebugEnabled = true;
    }

    public static void disableDebug(){
        _.DebugEnabled = false;
    }

    /**
     * Add test fields by types and view id.
     * @param viewId View id for the test field.
     * @param types Build in types
     * @return AndroidValidator instance.
     */
    public FormValidator putField(int viewId, Type... types){
        if (types.length < 1) throw new IllegalArgumentException("Types array at less 1 parameter !");
        Config s = Config.build(context, types[0]).apply();
        for (int i=1;i<types.length;i++){
            s.add(types[i]).apply();
        }
        formConfigArray.put(viewId, s);
        return this;
    }

    /**
     * Add a test field with config and view id.
     * @param viewId View id for the test field.
     * @param config Config
     * @return AndroidValidator instance.
     */
    public FormValidator putField(int viewId, Config config){
        formConfigArray.put(viewId, config);
        return this;
    }

    /**
     * Bind a form for test actions
     * @param form Target form layout
     * @return AndroidValidator instance.
     */
    public FormValidator bind(ViewGroup form){
        this.form = form;
        return this;
    }

    /**
     * Apply InputType to EditText.
     * @return AndroidValidator instance.
     */
    public FormValidator applyInputType(){
        checkBindForm();
        applyInputTypeToChildren(form);
        return this;
    }

    private void applyInputTypeToChildren(ViewGroup parent){
        int childrenCount = parent.getChildCount();
        for (int i = 0; i < childrenCount; i++){
            View child = parent.getChildAt(i);
            if ( ! (child instanceof EditText)){
                if (child instanceof ViewGroup) {
                    if (_.DebugEnabled) Log.i("Validator","[I] Found a ViewGroup child !");
                    applyInputTypeToChildren((ViewGroup) child);
                }
                // YES
                continue;
            }
            EditText item = (EditText) child;
            Config conf = formConfigArray.get(item.getId());
            if (conf == null){
                if (_.DebugEnabled) Log.w("Validator","[W] Apply InputType. A EditText child in Form without test config !");
                continue;
            }
            int inputType = InputType.TYPE_CLASS_TEXT;
            for (TestRunner r : conf.runnerArray){
                switch (r.testType){
                    case MobilePhone:
                    case Numeric:
                    case Digits:
                    case MaxValue:
                    case MinValue:
                    case RangeValue:
                    case IPv4:
                    case CreditCard:
                        inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL;
                        break;
                    case Email:
                        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
                        item.setSingleLine(true);
                        break;
                    case URL:
                    case Host:
                        inputType = InputType.TYPE_TEXT_VARIATION_URI;
                        break;
                    case MaxLength:
                    case RangeLength:
                        int index = Type.MaxLength.equals(r.testType) ? 0 : 1;
                        item.setFilters(new InputFilter[]{
                                new InputFilter.LengthFilter(r.extraInt[index])} );
                        break;
                    default: inputType = InputType.TYPE_CLASS_TEXT;
                }
            }
            item.setInputType(inputType);
        }
    }

    /**
     * Set all fields `single line`
     * @return AndroidValidator instance.
     */
    public FormValidator setSingleLine(){
        checkBindForm();
        int childrenCount = form.getChildCount();
        for (int i = 0; i < childrenCount; i++){
            View c = form.getChildAt(i);
            if (c instanceof EditText){
                EditText item = (EditText) c;
                item.setSingleLine(true);
            }
        }
        return this;
    }

    /**
     * Test all fields, and get a boolean result , STOP testing when got a test failed.
     * @return True if passed, false otherwise.
     */
    public boolean test(){
        checkBindForm();
        return testForm(form);
    }

    /**
     * Test all fields, and get a boolean reset.
     * @return True if passed, false otherwise.
     */
    public boolean testAll(){
        checkBindForm();
        return testFormAll(form);
    }

    /**
     * Test the form layout.
     * @param form The form layout
     * @param continueTest If true, continue when a filed test failed, otherwise break.
     * @return True when test passed .
     */
    private boolean testForm(ViewGroup form, boolean continueTest){
        valuesOfFields.clear();
        return testChildrenView(form, continueTest);
    }

    private boolean testChildrenView(ViewGroup parent, boolean continueTest){
        int childrenCount = parent.getChildCount();
        boolean testPassed = true;
        for (int i = 0; i < childrenCount; i++){
            View child = parent.getChildAt(i);
            if (child instanceof EditText){
                EditText item = (EditText) child;
                int viewId = item.getId();
                Config conf = formConfigArray.get(viewId);
                if (conf == null){
                    if (_.DebugEnabled) Log.w("Validator","[W] Running Test. A EditText child in Form without test config !");
                    continue;
                }
                ResultWrapper rs = testField(item, conf, display);
                testPassed &= rs.passed;
                valuesOfFields.put(viewId, rs.value);
                if (_.DebugEnabled) Log.i("Validator","[I] A field tested! Result:{ passed:"+testPassed +", message:"+rs.message +", value:"+rs.value+" }");
                if (! continueTest && ! testPassed) break;
            }else if ( child instanceof ViewGroup){
                if (_.DebugEnabled) Log.i("Validator","[I] Found a ViewGroup child !");
                testPassed &= testChildrenView((ViewGroup) child, continueTest);
            }else {
                if (_.DebugEnabled) Log.w("Validator","[W] A child in Form is NOT EditText !");
            }
        }
        return testPassed;
    }

    public boolean testForm(ViewGroup form){
        return testForm(form, false);
    }

    public boolean testFormAll(ViewGroup form){
        return testForm(form, true);
    }

    /**
     * Get value by view id.
     * @param viewId View Id.
     * @return String value in view.
     */
    public String getValue(int viewId){
        return valuesOfFields.get(viewId);
    }

    /**
     * Get value by view id from parent view.
     * @param parent Parent view
     * @param viewId view Id
     * @return String value in view.
     */
    public String getValue(View parent, int viewId){
        return ((TextView)parent.findViewById(viewId)).getText().toString();
    }

    /**
     * Set a custom display interface.
     * @param display display interface
     */
    public void setDisplay(MessageDisplay display){
        this.display = display;
    }

    /**
     * Test edit text field .
     * @param field Input field, a EditText view.
     * @param conf Test configuration .
     * @return Test result wrapper.
     */
    public static ResultWrapper testField(EditText field, Config conf, MessageDisplay display){
        if (conf == null) return new ResultWrapper(false,"Field configuration CANNOT BE NULL !!", null);
        boolean passed = true;
        String message = null;
        String input = String.valueOf(field.getText());
        if (display != null) display.dismiss(field);

        // If required
        TestRunner first = conf.runnerArray.get(0);
        if (Type.Required.equals(first.testType)){
            passed = first.perform(input);
            message = first.getMessage();
        }else if (TextUtils.isEmpty(input)){
            return new ResultWrapper(true, "NO-INPUT-VALUE-AND-IS-NOT-REQUIRED", String.valueOf(input));
        }

        if ( ! passed){
            if (display != null) display.show(field, message);
            return new ResultWrapper(false, message, null);
        }

        for (TestRunner r : conf.runnerArray){
            if (Type.Required.equals(r.testType)) continue;
            passed = r.perform(input);
            message = r.getMessage();
            if ( !passed){
                if (display != null) display.show(field, message);
                break;
            }
        }

        return new ResultWrapper(passed, message, String.valueOf(input));
    }

    private void checkBindForm(){
        if (form == null){
            throw new IllegalStateException("FormView is NULL ! Did you call 'bind(form)' ?");
        }
    }
}
