package com.smil.dcs.exception;

public class PCException extends DcsRuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5129089261853488220L;

    public PCException(String code, String msg) {
        super(code,msg);
    }

}
