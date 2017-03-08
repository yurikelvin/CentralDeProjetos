package exception;

public class CadastroException extends Exception {

	public CadastroException() {

	}

	public CadastroException(String message) {
		super(message);

	}

	public CadastroException(Throwable cause) {
		super(cause);

	}

	public CadastroException(String message, Throwable cause) {
		super(message, cause);

	}

	public CadastroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
