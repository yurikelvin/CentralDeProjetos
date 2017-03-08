package validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import exception.ValidacaoException;

public class Validacao {

	public static void validaString(String s) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException();
		}
		if(s.trim().equals("")) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validaString(String s, String b, String c, String msg) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException(msg);
		}
		if(s.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(b == null) {
			throw new NullPointerException(msg);
		}
		if(b.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(c == null) {
			throw new NullPointerException(msg);
		}
		if(c.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		
	}
	
	public static void validaString(String s, String b, String c, String a, String msg) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException(msg);
		}
		if(s.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(b == null) {
			throw new NullPointerException(msg);
		}
		if(b.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(c == null) {
			throw new NullPointerException(msg);
		}
		if(c.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(a == null) {
			throw new NullPointerException(msg);
		}
		if(a.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		
	}
	
	public static void validaString(String s, String msg) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException(msg);
		}
		if(s.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	public static void validaString(String s, String c, String msg) throws ValidacaoException{
		if(s == null) {
			throw new NullPointerException(msg);
		}
		if(s.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
		
		if(c == null) {
			throw new NullPointerException(msg);
		}
		if(c.trim().equals("")) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	
	public static void validaInt(int i) throws Exception{
		if(i < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validaInt(int i, String msg) throws ValidacaoException {
		if(i < 0) {
			throw new IllegalArgumentException(msg);
		}
	}
	
	/**
	 * Valida uma data passada por parametro no formato dd/MM/yyyy. A data so eh aceita se tiver no intervalo entre 01/01/2010 a 01/01/2025
	 * @param data Data a ser validada.
	 * @throws ValidacaoException Se a data for nula/vazia ou data no formato invalido.
	 * @throws ParseException Caso a transformacao da data passada para Date ocorra erro.
	 */
	
	public static void validaData(String data) throws ValidacaoException, ParseException {		String DATA_TIPO = "\\d\\d/\\d\\d/\\d\\d\\d\\d";
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
		
//		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
//		String result = out.format(dataAtual);
//		System.out.println(result);
	}
	

}
