package cdp.projetos;

import java.util.HashSet;

import cdp.exception.CadastroException;
import cdp.exception.DataException;
import cdp.exception.ValidacaoException;
import cdp.participacao.AlunoGraduando;
import cdp.participacao.AlunoPosGraduando;
import cdp.participacao.Participacao;
import cdp.participacao.Professor;

/**
 * Representa um projeto do tipo PED no sistema.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */
public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private HashSet<Produtividade> produtividades;
	

	private boolean temProfessorCoordenador;
	private boolean temProfessor;
	private boolean temAluno;
	
	
	public PED(String nomeDoProjeto, String categoria, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException, DataException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		this.setCategoria(categoria);
		this.produtividades = new HashSet<>();
		
	}
	
	public String getProdutividades() {
		return produtividades.toString();
	}
	
	/**
	 * O metodo vai pegar a quantidade de produtividade de um determinado tipo.
	 * @param produtividade
	 * @return
	 */
	public String getProdutividade(String prod) {
		for(Produtividade produtividade: this.produtividades) {
			if (produtividade.getProdutividade().equalsIgnoreCase(prod)) {
				return String.valueOf(produtividade.getQuantidade());
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
		Produtividade p = new Produtividade(produtividade, quantidade);
		
		produtividades.add(p);
	}
	
	/**
	 * O metodo vai definir a categoria do projeto PED.
	 * @param categoria
	 * @return
	 * @throws ValidacaoException
	 */
	public boolean setCategoria(String categoria) throws ValidacaoException {

		for(CategoriaPED categ: CategoriaPED.values()) {

			if(categoria.equalsIgnoreCase(categ.getValor())) {
				this.categoria = categ;
				return true;
			}
		}
		
		throw new ValidacaoException("Categoria invalida");
	}
	
	public CategoriaPED getCategoria() {
		return categoria;
	}

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		if(this.getCategoria() == CategoriaPED.COOPERACAO_EMPRESAS) { // TIPO COOPERACAO COM EMPRESAS
			
			this.cadastraCOOP(participacaoASerAdicionada);
			
			
		} else { // TIPO PIBIC, PIBITI e PIVIC
			
			this.cadastraOutros(participacaoASerAdicionada);	
			
		}
		
		super.participacoes.add(participacaoASerAdicionada);
					
	}

	private void cadastraCOOP(Participacao participacaoASerAdicionada) throws CadastroException {
		
		if(super.ehProfessor(participacaoASerAdicionada)) {  // inicio de adicao de professores COOPERACAO

			Professor prof = (Professor) participacaoASerAdicionada;
			if(prof.getCoordenador()) { // verifica se professor eh coordenador
				if(this.temProfessorCoordenador == false) { // verifica se o projeto ja contem um coordenador
					
					this.temProfessorCoordenador = true;
				} else {
					throw new CadastroException("Projetos P&D nao podem ter mais de um coordenador");
				}
			
			} else {
				if(this.temProfessorCoordenador) { // verifica se ja tem um coordenador cadastrado, para cadastrar o professor
					if(super.participacoes.contains(participacaoASerAdicionada)) {
						throw new CadastroException("Professor ja esta cadastrado nesse projeto");
					}
					
					
				} else {
					throw new CadastroException("Projetos P&D precisam de um coordenador");

				} // fim de adicao de professores cooperacao
		
			} 					
		} else {
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Aluno ja esta cadastrado nesse projeto");
			}
			
		}
		
	}
	
	private void cadastraOutros(Participacao participacaoASerAdicionada) throws CadastroException {

		
		if(super.ehProfessor(participacaoASerAdicionada)) {
			
			if(super.participacoes.contains(participacaoASerAdicionada)) {
				throw new CadastroException("Professor ja esta cadastrado nesse projeto");
			}
			
			if(temProfessor) {
				
				throw new CadastroException("Projetos P&D nao podem ter mais de um professor");

			} else {

				this.temProfessor = true;
			}
			
		} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
			if(temAluno) {
				
				throw new CadastroException("Projetos P&D nao podem ter mais de um graduando");
			
		
			} else {

				this.temAluno = true;
			}
			
		}
		
	}
	
	public void setTemAluno(boolean bool) {
		this.temAluno = bool;
	}
	
	public void setTemProfessor(boolean bool) {
		this.temProfessor = bool;
	}
	
	/**
	 * O metodo vai retornar todas as produtividades do projeto.
	 * @param descricao
	 * @return
	 * @throws ValidacaoException
	 */
	public String getRepresentacaoProdutividade(String descricao) throws ValidacaoException {
		for(Produtividade produtividade: this.produtividades) {
			if(produtividade.getProdutividade().equals(descricao)) {
				return Integer.toString(produtividade.getQuantidade());
			}
		}
		
		throw new ValidacaoException("Projeto nao tem essa produtividade");
	}

	public void setTemProfessorCoordenador(boolean b) {
		this.temProfessorCoordenador = b;
		
	}

	@Override
	public int geraContribuicao() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
