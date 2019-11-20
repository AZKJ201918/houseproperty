package com.azkj.houseproperty.common.exception;



public class PropertyExcption extends Exception {


    private  int OVERALL_SITUATTION=500;

    public PropertyExcption(String message) {
        super(message);
    }

    public PropertyExcption(Integer code, String message) {
        super(message);
        this.OVERALL_SITUATTION=code;
    }

    public int getStatusCode() {
        return OVERALL_SITUATTION;
    }
}
