package com.smil.dcs.exception;


/**
 * Created by ChenKuoJun on 2018-11-22.
 */
public class DcsRuntimeException extends  RuntimeException{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5494545757143234332L;
    
    /** 信息码. */
    private String code;
    
    /**
     * Instantiates a new am runtime exception.
     */
    public DcsRuntimeException() {
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public DcsRuntimeException(String message) {
        super(message);
    }

    public DcsRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param code the code
     * @param message the message
     */
    public DcsRuntimeException(String code, String message) {
        super(message);
    }

    public DcsRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }

    /**
     * Gets the 信息码.
     *
     * @return the 信息码
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the 信息码.
     *
     * @param code the new 信息码
     */
    public void setCode(String code) {
        this.code = code;
    }
}
