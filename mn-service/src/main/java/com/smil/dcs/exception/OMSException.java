package com.smil.dcs.exception;

public class OMSException extends DcsRuntimeException {
    public OMSException(String code, String msg) {
        super(code, msg);
    }
}
