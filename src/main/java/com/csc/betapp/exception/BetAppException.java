package com.csc.betapp.exception;

public class BetAppException extends Exception {

	private static final long serialVersionUID = -6253008943448118229L;

	public BetAppException() {
		super();
	}

	public BetAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BetAppException(String message) {
		super(message);
	}

	public BetAppException(Throwable cause) {
		super(cause);
	}
	
}
