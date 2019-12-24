package com.smil.dcs.exception;

public class CACException extends DcsRuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7699558133825345669L;

	public CACException(String code, String msg) {
        super(code, msg);
    }

}
