package com.github.chenyoca.validation;

import android.content.Context;

import com.github.chenyoca.validation.runners.RunnerFactory;
import com.github.chenyoca.validation.runners.TestRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * User: chenyoca@gmail.com
 * Date: 2014-06-25
 * Test configuration
 */
public class Config {

    public final List<TestRunner> runnerArray = new ArrayList<TestRunner>();

    private final Context context;
    private TestRunner configRunner;
    private Type lastType;

    private Config(Context context){
        this.context = context;
    }

    /**
     * Build a config by type
     * @param type Build in type
     * @return Config instance
     */
    public static Config build(Context context, Type type){
        Config c = new Config(context);
        c.add(type);
        return c;
    }

    /**
     * Build a config by a custom test runner
     * @param runner Custom runner
     * @return Config instance
     */
    public static Config build(Context context, TestRunner runner){
        Config c = new Config(context);
        c.add(runner);
        return c;
    }

    /**
     * Add a custom runner to config
     * @param runner Custom runner
     * @return Config instance
     */
    public Config add(TestRunner runner){
        autoCommit();
        configRunner = runner;
        return this;
    }

    public Config add(Type type){
        if (Type.Custom.equals(type)){
            throw new IllegalArgumentException("Type.Custom NOT Supported ! Use 'add(TestRunner runner)' please. ");
        }
        lastType = type;
        autoCommit();
        configRunner = RunnerFactory.build(context, type);
        return this;
    }

    public Config message(String message){
        configRunner.setMessage(message);
        return this;
    }

    public Config loader(LazyLoader loader){
        configRunner.setLazyLoader(loader);
        return this;
    }

    public Config values(int ...values){
        configRunner.setValues(values);
        return this;
    }

    public Config values(double ...values){
        configRunner.setValues(values);
        return this;
    }

    public Config values(String ...values){
        configRunner.setValues(values);
        return this;
    }

    public Config apply(){
        if (Type.Required.equals(lastType)){
            runnerArray.add(0, configRunner);
        }else{
            runnerArray.add(configRunner);
        }
        configRunner.onAdded();
        configRunner = null;
        return this;
    }

    private void autoCommit(){
        if (configRunner != null) apply();
    }

}
