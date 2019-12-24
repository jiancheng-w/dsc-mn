package com.smil.dcs.exception;

public class ICException extends DcsRuntimeException {

    /**
     */
    private static final long serialVersionUID = 222154569029453564L;

    public ICException(String code, String msg) {
        super(code,msg);
    }

}
