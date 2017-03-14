package validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exception.ValidacaoException;

/**
 * Classe desenvolvida para servir de validacao geral para o projeto.
 * 
 * @author Yuri Silva
 * @author Gustavo Victor
 * @author Tiberio Gadelha
 * @author
 *
 */

public class Validacao {
	
	/**
	 * Valida se uma String eh nula ou vazia.
	 * 
	 * @param string String a ser testada.
	 * @param msg Mensagem de erro.
	 * @throws ValidacaoException Caso for nula ou vazia.
	 */

	public static void validaString(String string, String msg) throws ValidacaoException{
		if(string == null || string.trim().equals("")) {
			throw new ValidacaoException(msg);
		}

	}
	
	/**
	 * Valida se um inteiro eh positivo.
	 * @param i Inteiro a ser testado.
	 * @param msg Mensagem de erro.
	 * @throws ValidacaoException Se o inteiro for negativo.
	 */
	
	public static void validaInt(int i, String msg) throws ValidacaoException {
		if(i < 0) {
			throw new ValidacaoException(msg);
		}
	}
	
	/**
	 * Valida uma data passada por parametro no formato dd/MM/yyyy. A data so eh aceita se tiver no intervalo entre 01/01/2010 a 01/01/2025
	 * @param data Data a ser validada.
	 * @throws ValidacaoException Se a data for nula/vazia ou data no formato invalido.
	 * @throws ParseException Caso a transformacao da data passada para Date ocorra erro.
	 */
	
	public static void validaData(String data) throws ValidacaoException, ParseException {	
		
		String DATA_TIPO = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
		
		if(data == null || data.trim().equals("")) {
			throw new ValidacaoException("Data nula ou vazia");
		}
	
		if (!(data.matches(DATA_TIPO))) {
			throw new ValidacaoException("Data invalida.");
		}
		
		Date dataPiso = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2010");
		Date dataTeto = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2025");
		Date dataPassada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		

		if(!(dataPassada.after(dataPiso) && dataPassada.before(dataTeto))) {
			throw new ValidacaoException();
		}
		
	}
	
	/**
	 * Valida uma data passada por parametro no formato dd/MM/yyyy. A data so eh aceita se tiver no intervalo entre 01/01/2010 a 01/01/2025
	 * @param data Data a ser validada.
	 * @param msg Mensagem de erro.
	 * @throws ValidacaoException Se a data for nula/vazia ou data no formato invalido.
	 * @throws ParseException Caso a transformacao da data passada para Date ocorra erro.
	 */
	
	public static void validaData(String data, String msg) throws ValidacaoException, ParseException {	
		
		String DATA_TIPO = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
		
		if(data == null || data.trim().equals("")) {
			throw new ValidacaoException(msg);
		}
	
		if (!(data.matches(DATA_TIPO))) {
			throw new ValidacaoException(msg);
		}
		
		Date dataPiso = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2010");
		Date dataTeto = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2025");
		Date dataPassada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		

		if(!(dataPassada.after(dataPiso) && dataPassada.before(dataTeto))) {
			throw new ValidacaoException(msg);
		}
		
	}
	
	/**
	 * Valida se um double eh positivo.
	 * @param valor Valor do double.
	 * @param msg Mensagem de erro.
	 * @throws ValidacaoException Caso o double seja negativo.
	 */
	
	public static void validaDouble(double valor, String msg) throws ValidacaoException {
		if(valor < 0) {
			throw new ValidacaoException(msg);
		}
	}
	
	/**
	 * Valida se um double eh positivo maior que zero.
	 * @param valor Valor do double.
	 * @param msg Mensagem de erro.
	 * @throws ValidacaoException Caso o double seja negativo.
	 */
	
	public static void validaDoubleSemZero(double valor, String msg) throws ValidacaoException {
		if(valor <= 0) {
			throw new ValidacaoException(msg);
		}
	}
	
	
	/**
	 * Valida um inteiro menor ou igual a zero.
	 * @param valor Valor do inteiro.
	 * @param msg Mensagem de Erro.
	 * @throws ValidacaoException Caso o numero seja menor ou igual a zero.
	 */
	public static void validaIntSemZero(int valor, String msg) throws ValidacaoException {
		if(valor <= 0) {
			throw new ValidacaoException(msg);
		}
	}

}
