package com.smil.dcs.exception;

/**
 * @author kuojun.chen
 * @date 2019/01/11 15:17
 * @description:
 */
public class WmsRuntimeException extends  RuntimeException {

    private static final long serialVersionUID = -1408031202674327628L;
    /** 信息码. */
    private String code;

    /**
     * Instantiates a new am runtime exception.
     */
    public WmsRuntimeException() {
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public WmsRuntimeException(String message) {
        super(message);
    }

    public WmsRuntimeException(Throwable cause) {
        super(cause);
    }



    public WmsRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param code the code
     * @param message the message
     */
    public WmsRuntimeException(String code, String message) {
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
