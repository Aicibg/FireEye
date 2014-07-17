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
    private TestRunner configingRunner;
    private Types lastType;

    private Config(Context context){
        this.context = context;
    }

    /**
     * Build a config by type
     * @param type Build in type
     * @return Config instance
     */
    public static Config build(Context context, Types type){
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
        configingRunner = runner;
        return this;
    }

    public Config add(Types type){
        lastType = type;
        autoCommit();
        configingRunner = RunnerFactory.build(context, type);
        return this;
    }

    public Config message(String message){
        configingRunner.setMessage(message);
        return this;
    }

    public Config loader(LazyLoader loader){
        configingRunner.setLazyLoader(loader);
        return this;
    }

    public Config values(int ...values){
        configingRunner.setValues(values);
        return this;
    }

    public Config values(double ...values){
        configingRunner.setValues(values);
        return this;
    }

    public Config values(String ...values){
        configingRunner.setValues(values);
        return this;
    }

    public Config apply(){
        if (Types.Required.equals(lastType)){
            runnerArray.add(0, configingRunner);
        }else{
            runnerArray.add(configingRunner);
        }
        configingRunner.onAdded();
        configingRunner = null;
        return this;
    }

    private void autoCommit(){
        if (configingRunner != null) apply();
    }

}
