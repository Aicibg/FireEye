package com.github.yoojia.fireeye;

public class TestResult {

    public final boolean passed;
    public final String message;
    public final String error;
    final String value;

    public TestResult(boolean passed, String message,String error, String value){
        this.passed = passed;
        this.message = message;
        this.error = error;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ " +
                "passed: " + passed + ",\t" +
                "value: "+ ( value == null ? "NO_VALUE" : value ) + ", " +
                "message: " + message +
                "error: " + error +
                "}";
    }
}