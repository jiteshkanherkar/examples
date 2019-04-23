package org.jitesh.appstatistics.serverstats.exception;

public class ServerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServerNotFoundException() {
		super();
	}

	public ServerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServerNotFoundException(String message) {
		super(message);
	}

	public ServerNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
