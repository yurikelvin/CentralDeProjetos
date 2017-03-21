package cdp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cdp.exception.ValidacaoException;

/**
 * Classe responsavel por fazer a validacao unicamente de Pessoa.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 *
 */

public class ValidaPessoa {
	
	/**
	 * Faz a validacao do nome da pessoa. So eh permitido nome com letras e espacos.
	 * @param nome Nome da pessoa a ser validada.
	 * @throws ValidacaoException Caso o nome da pessoa seja nulo/vazio ou contenha caracteres invalidos.
	 */
	
	public static void validaNome(String nome) throws ValidacaoException{
		if (nome == null || nome.trim().equals("")) {
			throw new ValidacaoException("Nome nulo ou vazio");
		}
		
		for (int i = 0; i < nome.length(); i++) {
			char letra = nome.charAt(i);
			if (!(Character.isAlphabetic(letra) || Character.isWhitespace(letra))) {
				throw new ValidacaoException("Nome invalido");

			}
		}
		
		
	}
	
	/**
	 * Valida um cpf e valida o formato ddd.ddd.ddd-dd. IMPORTANTE: Eh possivel validar o real cpf da pessoa se descomentar o codigo.
	 * @param cpf CPF da Pessoa
	 * @throws ValidacaoException Caso o cpf da pessoa for nulo/vazio ou cpf nao seja do formato correto. 
	 */
	
	public static void validaCPF(String cpf) throws ValidacaoException {
		if(cpf == null || cpf.trim().equals("")) {
			throw new ValidacaoException("CPF nulo ou vazio");
		}
		
		String TIPO_CPF = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";

		
		if(!cpf.matches(TIPO_CPF)) {

			
			throw new ValidacaoException("CPF invalido");
		}
		

//		if(!validaDigitoVerificador(cpf)) {
//			throw new ValidacaoException("Cpf invalido.");
//		}
	
	}
	
	/**
	 * Faz a real validacao de um cpf valido.
	 * 
	 * @param cpf Cpf a validar.
	 * @return True se o cpf for verdadeiro.
	 */
	
	private static boolean validaDigitoVerificador(String cpf) {
		int soma = 0;
		int decrementador = 10;
		int SUPOSTO_PRIMEIRO_DIGITO = Character.getNumericValue(cpf.charAt(cpf.length() - 2));
		int SUPOSTO_SEGUNDO_DIGITO = Character.getNumericValue(cpf.charAt(cpf.length() - 1));
		for(int i = 0 ; i < (cpf.length() - 2); i ++ ) {
			if(Character.isDigit(cpf.charAt(i))) {
				soma += Character.getNumericValue(cpf.charAt(i)) * decrementador;

				decrementador --;
			}
		}
		
		int primeiroDigito;

		int modPrimeiroDigito = soma % 11;
		
		if(modPrimeiroDigito < 2) {
			primeiroDigito = 0;
		} else {
			primeiroDigito = 11 - modPrimeiroDigito;
		}
		
		soma = 0;
		decrementador = 11;
		
		for(int i = 0; i < (cpf.length() - 2); i ++) {
			if(Character.isDigit(cpf.charAt(i))) {
				soma += Character.getNumericValue(cpf.charAt(i)) * decrementador;
				decrementador --;
			}
		}
		
		soma += primeiroDigito * 2;
		
		int modSegundoDigito = soma % 11;
		
		int segundoDigito;
		if(modSegundoDigito < 2) {
			segundoDigito = 0;
		} else {
			segundoDigito = 11 - modSegundoDigito;
		}
		
		if( primeiroDigito == SUPOSTO_PRIMEIRO_DIGITO && segundoDigito == SUPOSTO_SEGUNDO_DIGITO) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Valida um email passado. Verifica se contem @ (arroba) no email.
	 * @param email O email a ser validado.
	 * @throws ValidacaoException Caso email seja nulo/vazio ou email nao contenha @ (arroba) .
	 */
	
	public static void validaEmail(String email) throws ValidacaoException {
		if(email == null || email.trim().equals("")) {
			throw new ValidacaoException("Email nulo ou vazio");
		}
		
		String EMAIL_PATTERN = 
		        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			throw new ValidacaoException("Email invalido");
		}

	}
	
	

}
