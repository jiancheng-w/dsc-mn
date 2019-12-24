package com.smil.dcs.exception;

/**
 * @author jianjun.li
 * @date 2019/01/11 15:17
 * @description:
 */
public class LmRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -1408031202674327628L;
    /** 信息码. */
    private int code;

    /**
     * Instantiates a new am runtime exception.
     */
    public LmRuntimeException() {
    }
    
    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public LmRuntimeException(String message) {
        super(message);
        this.code = 500;
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public LmRuntimeException(int code, String message) {
        super(message);
        this.code = code;
    }

    public LmRuntimeException(int code, String message,Throwable cause) {
        super(message,cause);
        this.code = code;
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param code the code
     * @param message the message
     */
    public LmRuntimeException(String code, String message) {
        super(message);
    }

    /**
     * Gets the 信息码.
     *
     * @return the 信息码
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the 信息码.
     *
     * @param code the new 信息码
     */
    public void setCode(int code) {
        this.code = code;
    }
}
