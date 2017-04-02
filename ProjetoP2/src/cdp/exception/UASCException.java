package cdp.exception;

/**
 * Excessao criada caso ocorra erro na classe de financas da UASC.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class UASCException extends Exception {

	public UASCException() {
		
	}

	public UASCException(String message) {
		super(message);
		
	}

	public UASCException(Throwable cause) {
		super(cause);
		
	}

	public UASCException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UASCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
