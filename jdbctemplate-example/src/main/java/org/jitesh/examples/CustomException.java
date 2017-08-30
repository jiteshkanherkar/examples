package org.jitesh.examples;

public class CustomException extends Exception {

	private String msg;

	public CustomException() {
		super("Custom Exception");
	}

	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public CustomException(Exception e) {
		super(e);
	}

}
