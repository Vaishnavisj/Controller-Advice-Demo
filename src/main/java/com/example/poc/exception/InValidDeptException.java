package com.example.poc.exception;

public class InValidDeptException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidDeptException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InValidDeptException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InValidDeptException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InValidDeptException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InValidDeptException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
