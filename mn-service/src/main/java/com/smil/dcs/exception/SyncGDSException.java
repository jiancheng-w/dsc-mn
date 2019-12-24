package com.smil.dcs.exception;

public class SyncGDSException extends RuntimeException {
    private String code;

    public SyncGDSException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public SyncGDSException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
