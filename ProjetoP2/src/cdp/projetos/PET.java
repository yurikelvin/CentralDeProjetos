package cdp.projetos;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;

/**
 * Representacao de um projeto do tipo PET no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class PET extends Projeto {
	
	private int rendimento;
	private ImpactoSocial impactoSocial;
	private Set<Produtividade> produtividades;
	
	private boolean temTutor;
	

	
	public PET(String nomeDoProjeto, String objetivoDoProjeto, int impactoSocial, int rendimento,  String dataInicio, int duracao, int codigo) throws DataException, ValidacaoException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setImpacto(impactoSocial);
		this.rendimento = rendimento;
		produtividades = new HashSet<>();
		
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
	
	public String getProdutividades() {
		return produtividades.toString();
	}
	
	/**
	 * O metodo vai pegar a quantidade de produtividade de um determinado tipo.
	 * @param produtividade
	 * @return
	 */
	public String getProdutividade(String produtividade) {
		for(Produtividade p: produtividades) {
			if (p.getProdutividade().equalsIgnoreCase(produtividade)) {
				return String.valueOf(p.getQuantidade());
			}
		}
		return null;
	}
	
	/**
	 * Adiciona uma quantidade de produtividade ao projeto, que no caso, pode ser prodAcademica, prodTecnica e patentes.
	 * @param produtividade
	 * @param quantidade
	 */
	public void adicionaProdutividade(String produtividade, int quantidade) {
		Iterator<Produtividade> it = this.produtividades.iterator();
		while(it.hasNext()) {
			Produtividade prod = it.next();
			if(prod.getProdutividade().equalsIgnoreCase(produtividade)) {
				it.remove();
				break;
			}
		}
		Produtividade p = new Produtividade(produtividade, quantidade);
		produtividades.add(p);
	}
	
	public int getRendimento(){
		return rendimento;
	}
	
	public ImpactoSocial getImpactoSocial() {
		return impactoSocial;
	}
	
	public void atualizaDespesasProjeto(double montanteBolsas, double montanteCusteio, double montanteCapital) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", montanteCusteio);
		this.setDespesa("bolsa", montanteBolsas);
	}
	
	public void setBolsa(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("bolsa", valor);
	}
	
	public void setCusteio(double valor) throws ValidacaoException, CadastroException {
		this.setDespesa("custeio", valor);
	}
	
	public String getRepresentacaoRendimento() {
		return Integer.toString(this.getRendimento());
	}

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException, ValidacaoException {
		
		this.cadastroPet(participacaoASerAdicionada);
		super.participacoes.add(participacaoASerAdicionada);
		
	}
	
	private void cadastroPet(Participacao participacaoASerAdicionada) throws CadastroException, ValidacaoException {
		
		if(super.ehProfessor(participacaoASerAdicionada)) {
			
			Professor prof = (Professor) participacaoASerAdicionada;
			
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Professor ja esta cadastrado nesse projeto");
			}
			
			if(prof.getCoordenador() && this.temTutor == false) {
				this.temTutor = true;
			} else {
				throw new CadastroException("Projetos PET nao podem ter mais de um coordenador");
			}
		} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
			}
			
		} else if(participacaoASerAdicionada instanceof AlunoPosGraduando) {
			throw new CadastroException("Tipo de projeto invalido para pos graduando");
		} else {
			throw new CadastroException("Tipo de projeto invalido para profissional");
		}
		
		if(super.participacoes.contains(participacaoASerAdicionada)) {
			throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
		}
		
		super.verificaParticipacao(participacaoASerAdicionada.getCpf(), participacaoASerAdicionada.getCodigoProjeto());
	}
	
	public void setTemTutor(boolean b) {
		this.temTutor = b;
	}
	
	/**
	 * O metodo vai retornar todas as produtividades do projeto.
	 * @param descricao
	 * @return
	 * @throws ValidacaoException
	 */
	public String getRepresentacaoProdutividade(String descricao) throws ValidacaoException {
		for(Produtividade produtividade: this.produtividades) {
			if(produtividade.getProdutividade().equalsIgnoreCase(descricao)) {
				return Integer.toString(produtividade.getQuantidade());
			}
		}
		
		throw new ValidacaoException("Projeto nao tem essa produtividade");
	}

	@Override
	public void removeParticipacao(Participacao participacaoASerRemovida) throws CadastroException {
		
		if(participacaoASerRemovida instanceof Professor) {
			this.setTemTutor(false);
		}
		
		
		boolean removeu = super.participacoes.remove(participacaoASerRemovida);
		
		if(!removeu) {
			throw new CadastroException("Participacao nao encontrada");
		}
		
	}

	@Override
	public double geraContribuicao() {
		
		return 0.0;
	}
	

}
