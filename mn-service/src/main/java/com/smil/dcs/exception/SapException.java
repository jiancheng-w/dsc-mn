package com.smil.dcs.exception;

public class SapException extends  RuntimeException {
    private String code;

    public SapException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
