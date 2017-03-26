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
public class Extensao extends Projeto {
	
	private static double PERCENTUAL_BASE = 10.0;
	private final static double PERCENTUAL_IMPACTO_SOCIAL = 0.5;
	
	private ImpactoSocial impactoSocial;
	private boolean temProfessorCoordenador;
	
	public Extensao(String nomeProjeto, String objetivoDoProjeto, int impacto, String dataInicio, int duracao, int codigo) throws DataException {
		super(nomeProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setImpacto(impacto);
		
	}
	
	public void setProfessor(boolean b) {
		this.temProfessorCoordenador = b;
	}

	public ImpactoSocial getImpacto() {
		return impactoSocial;
	}
	
	public void atualizaDespesasProjeto(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", montanteCusteio);
		this.setDespesa("bolsa", montanteBolsas);
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
		
		this.cadastraExtensao(participacaoASerAdicionada);
		
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
		
		super.verificaParticipacao(participacaoASerAdicionada.getCpf(), participacaoASerAdicionada.getCodigoProjeto());
	}
	
	@Override
	public void removeParticipacao(Participacao participacaoASerRemovida) throws CadastroException {
		
		if(participacaoASerRemovida instanceof Professor) {
			this.setProfessor(false);
		}
		
		boolean removeu = super.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new ValidacaoException("Participacao nao encontrada");
		}
		
	}

	@Override
	public double geraContribuicao() {
		
		double valorAReduzir = impactoSocial.getValorImpactoSocial() * PERCENTUAL_IMPACTO_SOCIAL;
		PERCENTUAL_BASE -= valorAReduzir;
	
		double baseDaContribuicao = super.getDespesa("custeio") + super.getDespesa("bolsa");
		
		if (baseDaContribuicao <= 10000){
			return 0;
		}
	
		return (baseDaContribuicao * PERCENTUAL_BASE)/100.0;
	}

}
