package cdp.uasc;

import cdp.controllers.ProjetoController;
import cdp.exception.CadastroException;
import cdp.exception.ValidacaoException;
import cdp.utils.Validacao;

public class FinancasAcademicas {
	
	private static double receita;
	
	private ProjetoController projetoController;
	
	public FinancasAcademicas(ProjetoController projetoController) {

		this.projetoController = projetoController;
	}
	
	public double getReceita() {
		return receita;
	}
	
	public void aumentaReceita(double valor) throws ValidacaoException{
		Validacao.validaDouble(valor, "valor negativo");
		
		receita += valor;
	}
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		
		Validacao.validaDouble(valor, "valor negativo");
		
		if(receita - valor < 0) {
			throw new ValidacaoException("a unidade nao possui fundos suficientes");
		}
		
		receita -= valor;
	}
	
	public double calculaColaboracaoUASC(int codigoProjeto) throws ValidacaoException, CadastroException  {
		double colaboracao = projetoController.calculaColaboracaoUASC(codigoProjeto);
		
		this.aumentaReceita(colaboracao);
		
		return colaboracao;
	}

}
