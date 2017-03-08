package projetos;

import java.util.ArrayList;
import java.util.HashMap;

import exception.CadastroException;
import exception.ValidacaoException;
import validacao.Validacao;

public class Despesa {
	
	private static final String BOLSA = "bolsa";
	private static final String CUSTEIO = "custeio";
	private static final String CAPITAL = "capital";
	
	
	private HashMap<String, Double> despesas;
	
	public Despesa() {
		despesas = new HashMap<>();
		
		despesas.put(BOLSA, 0.0);
		despesas.put(CAPITAL, 0.0);
		despesas.put(CUSTEIO, 0.0);
	}

	public void setDespesa(String descricao, double valor) throws ValidacaoException, CadastroException{
		Validacao.validaString(descricao, "Despesa nao valida");
		
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
	
	public double getDespesaTotal() {
		double total = 0.0;
		for(Double d: despesas.values()) {
			total += d;
		}
		
		return total;
	}
	
	public double getDespesa(String descricao) throws ValidacaoException {
		Validacao.validaString(descricao, "Despesa nao valida");
		
		if(despesas.get(descricao.toLowerCase()) == null) {
			throw new ValidacaoException("Despesa invalida");
		}
		
		return despesas.get(descricao.toLowerCase());
	}
	
	

}
