package cdp.uasc;

import java.io.Serializable;

import cdp.controllers.ProjetoController;
import cdp.exception.CadastroException;
import cdp.exception.UASCException;
import cdp.exception.ValidacaoException;
import cdp.utils.Validacao;

/**
 * Classe responsavel por representar o setor financeiro da UASC.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class FinancasAcademicas implements Serializable {
	
	private double receita;
	
	private double totalContribuicao;
	
	private ProjetoController projetoController;
	
	public FinancasAcademicas(ProjetoController projetoController) {

		this.projetoController = projetoController;
	}
	
	public double getReceita() {
		return receita;
	}
	
	private void aumentaReceita(double valor) throws ValidacaoException{
		Validacao.validaDouble(valor, "valor negativo");
		
		totalContribuicao += valor;
		receita += valor;
	}
	
	public void diminuiReceita(double valor) throws ValidacaoException {
		
		Validacao.validaDouble(valor, "valor negativo");
		
		if(receita - valor < 0) {
			throw new ValidacaoException("a unidade nao possui fundos suficientes");
		}
		
		receita -= valor;
	}
	
	public double calculaColaboracaoUASC(String codigoProjeto) throws ValidacaoException, CadastroException, UASCException  {
		
		Validacao.validaRepresentacaoCodigoProjetoUASC(codigoProjeto, "codigo nulo ou vazio");
		
		double colaboracao = projetoController.calculaColaboracaoUASC(Integer.parseInt(codigoProjeto));
		
		this.aumentaReceita(colaboracao);
		
		return colaboracao;
	}
	
	public double getTotalContribuicao() {
		return totalContribuicao;
	}

}
