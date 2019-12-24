package com.smil.dcs.exception;

/**
 * @author duankk
 * @date 2019/6/5 11:00
 * @description:
 */
public class OMSRuntimeException extends  RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8477171965378188841L;

    /** 信息码. */
    private String code;

    /**
     * Instantiates a new am runtime exception.
     */
    public OMSRuntimeException() {
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public OMSRuntimeException(String message) {
        super(message);
    }

    public OMSRuntimeException(String message, Throwable cause) {
        super(message,cause);
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param code the code
     * @param message the message
     */
    public OMSRuntimeException(String code, String message) {
        super(message);
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
