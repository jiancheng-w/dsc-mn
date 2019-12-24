package com.smil.dcs.exception;

/**
 * @author kuojun.chen
 * @date 2018/12/19 10:41
 * @description:
 */
public class SysRuntimeException extends  RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2121933974425120921L;

    /** 信息码. */
    private String code;

    /**
     * Instantiates a new am runtime exception.
     */
    public SysRuntimeException() {
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param message the message
     */
    public SysRuntimeException(String message) {
        super(message);
    }

    public SysRuntimeException(String message,Throwable cause) {
        super(message,cause);
    }

    /**
     * Instantiates a new am runtime exception.
     *
     * @param code the code
     * @param message the message
     */
    public SysRuntimeException(String code, String message) {
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
