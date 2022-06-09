package fr.oz.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = -7605746060992941965L;

	public CustomException() {
		super();

	}

	public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);

	}

	public CustomException(String message) {
		super(message);

	}

	public CustomException(Throwable cause) {
		super(cause);

	}

}
