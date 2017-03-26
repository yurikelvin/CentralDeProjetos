package cdp.uasc;

import cdp.exception.ValidacaoException;

public class UASC {
	
	private double receita;
	
	public UASC() {
		receita = 0;
	}
	
	public double getReceita() {
		return receita;
	}
	
	public void aumentaReceita(double valor){
		
		receita += valor;
	}
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		if(valor < 0) {
			throw new ValidacaoException("Erro na atualizacao da receita da unidade: valor negativo");
		}
		
		if(receita - valor < 0) {
			throw new ValidacaoException("Erro na atualizacao da receita da unidade: a unidade nao possui fundos suficientes");
		}
		receita -= valor;
	}

}
