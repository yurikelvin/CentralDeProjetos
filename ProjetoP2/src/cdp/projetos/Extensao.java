package cdp.projetos;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;

/**
 * Representacao de um projeto do tipo Extensao no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class Extensao extends Projeto implements Contribuicao {
	
	private ImpactoSocial impactoSocial;
	private boolean temProfessorCoordenador;
	
	public Extensao(String nomeProjeto, String objetivoDoProjeto, int impacto, String dataInicio, int duracao, int codigo) throws DataException {
		super(nomeProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setImpacto(impacto);
		
	}
	
	public void setProfessor(boolean b) {
		this.temProfessorCoordenador = b;
	}

	public String getImpacto() {
		return impactoSocial.getImpactoSocial();
	}
	
	public void setCusteio(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", valor);
	}
	
	public void setBolsa(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("bolsa", valor);
	}
	
	/**
	 * O metodo vai classificar o impacto social desse projeto.
	 * @param impacto
	 * @return
	 * @throws ValidacaoException
	 */
	public boolean setImpacto(int impacto) throws ValidacaoException {
		for (ImpactoSocial impac : ImpactoSocial.values()) {
			if(impac.getValorImpactoSocial() == impacto) {
				this.impactoSocial = impac;
				return true;
			}
		}
		
		throw new ValidacaoException("Impacto social invalido");
	}

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		cadastraExtensao(participacaoASerAdicionada);
		
		super.participacoes.add(participacaoASerAdicionada);
		
	}
	
	private void cadastraExtensao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		if(super.participacoes.contains(participacaoASerAdicionada)) {
			throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
		}
		if(super.ehProfessor(participacaoASerAdicionada)) {
			Professor prof = (Professor) participacaoASerAdicionada;
			if(prof.getCoordenador() && this.temProfessorCoordenador == false) {
				this.temProfessorCoordenador = true;
			} else {
				throw new CadastroException("Projetos PET nao podem ter mais de um coordenador");
			}
		} 
	}

}
