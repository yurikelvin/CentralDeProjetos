package projetos;

import java.io.Serializable;
import java.util.HashMap;

import exception.CadastroException;
import exception.ValidacaoException;
import validacao.Validacao;

/**
 * Classe responsavel por simbolizar a despesa de cada projeto.
 * 
 * @author Yuri Silva
 * @author Gustavo Victor
 * @author
 * @author
 */

public class Despesa implements Serializable {
	
	private static final String BOLSA = "bolsa";
	private static final String CUSTEIO = "custeio";
	private static final String CAPITAL = "capital";
	
	
	private HashMap<String, Double> despesas;
	
	/**
	 * Cria uma Despesa setando ja nela os valores 0 para as despesas.
	 */
	public Despesa() {
		despesas = new HashMap<>();
		
		despesas.put(BOLSA, 0.0);
		despesas.put(CAPITAL, 0.0);
		despesas.put(CUSTEIO, 0.0);
	}
	
	/**
	 * Adiciona uma certa despesa ao projeto indicando seu valor.
	 * 
	 * @param descricao Tipo de despesa.
	 * @param valor Valor a ser adicionado ao tipo de despesa.
	 * @throws ValidacaoException Caso o tipo de despesa nao seja valido.
	 * @throws CadastroException Caso o tipo de despesa ja tenha sido definido.
	 */

	public void setDespesa(String descricao, double valor) throws ValidacaoException, CadastroException{
		Validacao.validaString(descricao, "Despesa invalida");
		
		switch(descricao.toLowerCase()) {
			case BOLSA:
				if(despesas.get(BOLSA) > 0) {
					throw new CadastroException("Despesa de bolsa ja foi definida");
				}
				despesas.put(BOLSA, valor);
				break;
			case CAPITAL:
				if(despesas.get(CAPITAL) > 0) {
					throw new CadastroException("Despesa de bolsa ja foi definida");
				}
				despesas.put(CAPITAL, valor);
				break;
			case CUSTEIO:
				if(despesas.get(CUSTEIO) > 0) {
					throw new CadastroException("Despesa de bolsa ja foi definida");
				}
				despesas.put(CUSTEIO, valor);
				break;
			default:
				throw new ValidacaoException("Despesa invalida");
		}
	}
	
	/**
	 * Retorna o custo gerado pelas despesas.
	 * @return O custo gerado pelas despesas.
	 */
	
	public double getDespesaTotal() {
		double total = 0.0;
		for(Double d: despesas.values()) {
			total += d;
		}
		
		return total;
	}
	
	/**
	 * Retorna o custo de um determinado tipo de despesa.
	 * @param descricao Tipo de despesa.
	 * @return O custo de um determinado tipo de despesa.
	 * @throws ValidacaoException Caso a despesa seja invalida.
	 */
	
	public double getDespesa(String descricao) throws ValidacaoException {
		Validacao.validaString(descricao, "Despesa invalida");
		
		if(despesas.get(descricao.toLowerCase()) == null) {
			throw new ValidacaoException("Despesa invalida");
		}
		
		return despesas.get(descricao.toLowerCase());
	}
	
	

}
