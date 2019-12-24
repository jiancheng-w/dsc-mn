package com.smil.dcs.exception;

public class ForeignCodeNotFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 4817894817031691560L;
	
	private int errorCode;
    private String errorMessage;

    public ForeignCodeNotFoundException(String errorMessage) {
        this.errorCode = 8001;
        this.errorMessage = errorMessage;
     }
    
    public ForeignCodeNotFoundException(int errorCode, String errorMessage) {
       this.errorCode = errorCode;
       this.errorMessage = errorMessage;
    }

    /**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
