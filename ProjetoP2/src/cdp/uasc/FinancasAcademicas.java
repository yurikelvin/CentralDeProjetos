package cdp.uasc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	private static final String FIM_DE_LINHA = System.lineSeparator();
	
	private double totalGasto;
	
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
		totalGasto+= valor;
		receita -= valor;
	}
	
	public double calculaColaboracaoUASC(String codigoProjeto) throws ValidacaoException, CadastroException, UASCException  {
		
		Validacao.validaRepresentacaoCodigoProjetoUASC(codigoProjeto, "codigo nulo ou vazio");
		
		double colaboracao = projetoController.calculaColaboracaoUASC(Integer.parseInt(codigoProjeto));
		
		this.aumentaReceita(colaboracao);
		
		return colaboracao;
	}
	
	public String geraRelatorioColaboracoes() throws CadastroException, ValidacaoException, IOException{
		String relatorio = "";
		relatorio += projetoController.geraHistoricoColaboracaoUasc() + "..." + FIM_DE_LINHA + "Total acumulado com colaboracoes: R$" + this.totalContribuicao + FIM_DE_LINHA + "Total gasto: R$" + this.totalGasto + FIM_DE_LINHA + "Total em caixa: R$" + this.receita;
		this.salvaRelatorio(relatorio);
		return relatorio;
		
	}
	
	private void salvaRelatorio(String relatorio) throws IOException{
		PrintWriter arquivo = null;
		
		try {
			arquivo = new PrintWriter(new BufferedWriter(new FileWriter("arquivos_sistema/relatorios/cad_colaboracoes.txt")));
			arquivo.println(relatorio);
		} finally {
			if(arquivo != null) {
				arquivo.close();
			}
			
		}
		
	}
	public double getTotalContribuicao() {
		return totalContribuicao;
	}

}
