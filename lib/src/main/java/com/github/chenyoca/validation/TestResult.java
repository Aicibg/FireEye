package com.github.chenyoca.validation;

public class TestResult {

    public final boolean passed;
    public final String message;
    final String value;

    public TestResult(boolean passed, String message, String value){
        this.passed = passed;
        this.message = message;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{ " +
                "passed: "+passed+",\t" +
                "value:"+(value==null?"":value)+", "+
                "message: "+message+
                "}";
    }
}