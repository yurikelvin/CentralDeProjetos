package cdp.exception;

/**
 * Excessao criada caso ocorra algum erro de cadastro.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

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
