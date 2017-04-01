package cdp.factorys;

import java.io.IOException;
import java.io.Serializable;

import cdp.controllers.PessoaController;
import cdp.controllers.ProjetoController;
import cdp.exception.CadastroException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;
import cdp.participacao.Profissional;

/**
 * Classe responsavel pelo encapsulamento da criacao de uma participacao.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public class FactoryDeParticipacao implements Serializable {
	
	private PessoaController pessoaController;
	private ProjetoController projetoController;

	public FactoryDeParticipacao(PessoaController pessoaController, ProjetoController projetoController) {
		
		this.pessoaController = pessoaController;
		this.projetoController = projetoController;
	}
	
	public Participacao criaAssociacaoProfessor(String cpf, int codigoProjeto, boolean coordenador, double valorHora, int qtdHoras) throws ValidacaoException, CadastroException {
	
		this.projetoController.validaHoraProfessor(valorHora, codigoProjeto);
		
		Professor participacao = new Professor(valorHora, qtdHoras, coordenador);
		
				
		this.pessoaController.associaPessoa(participacao, cpf);
		this.projetoController.associaProjeto(participacao, codigoProjeto);
		

		this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
		this.pessoaController.adicionaParticipacao(participacao, cpf);
		
		return participacao;
	}
	
	public Participacao criaAssociacaoGraduando(String cpf, int codigoProjeto, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		AlunoGraduando participacao = new AlunoGraduando(valorHora, qtdHoras);
		
		this.pessoaController.associaPessoa(participacao, cpf);
		this.projetoController.associaProjeto(participacao, codigoProjeto);
		

		this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
		this.pessoaController.adicionaParticipacao(participacao, cpf);
		
		return participacao;
	}
	
	public Participacao criaAssociacaoProfissional(String cpf, int codigoProjeto, String cargo, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		Profissional participacao = new Profissional(cargo.toLowerCase(), valorHora, qtdHoras);
		
		this.pessoaController.associaPessoa(participacao, cpf);
		this.projetoController.associaProjeto(participacao, codigoProjeto);
		

		this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
		this.pessoaController.adicionaParticipacao(participacao, cpf);
		
		return participacao;
	}
	
	public Participacao criaAssociacaoPosGraduando(String cpf, int codigoProjeto, String associacao, double valorHora, int qtdHoras) throws CadastroException, ValidacaoException {
		AlunoPosGraduando participacao = new AlunoPosGraduando( valorHora, qtdHoras, associacao);

		this.pessoaController.associaPessoa(participacao, cpf);
		this.projetoController.associaProjeto(participacao, codigoProjeto);
		

		this.projetoController.adicionaParticipacao(participacao, codigoProjeto);
		this.pessoaController.adicionaParticipacao(participacao, cpf);
		
		return participacao;
	}
	
	

}
