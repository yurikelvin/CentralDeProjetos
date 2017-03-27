package cdp.exception;

/**
 * Excessao criada caso ocorra algum erro de validacao.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class ValidacaoException extends Exception {

	public ValidacaoException() {

	}

	public ValidacaoException(String message) {
		super(message);

	}

	public ValidacaoException(Throwable cause) {
		super(cause);

	}

	public ValidacaoException(String message, Throwable cause) {
		super(message, cause);

	}

	public ValidacaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
