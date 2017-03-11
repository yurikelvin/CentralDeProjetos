package projetos;

import java.util.HashSet;

import exception.CadastroException;
import exception.ValidacaoException;
import participacao.AlunoGraduando;
import participacao.Participacao;
import participacao.Professor;

public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private HashSet<Produtividade> produtividades;
	

	boolean temProfessorCoordenador;
	boolean temProfessor;
	boolean temAluno;
	

	public PED(String nomeDoProjeto, String categoria, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		setCategoria(categoria);
		this.produtividades = new HashSet<>();
		
	}
	
	public String getProdutividades() {
		return produtividades.toString();
	}
	
	public int getProdutividade(String produtividade) {
		for(Produtividade p: produtividades) {
			if (p.getProdutividade().equalsIgnoreCase(produtividade)) {
				return p.getQuantidade();
			}
		}
		return 0;
	}
	
	public void adicionaProdutividade(String produtividade, int quantidade) {
		Produtividade p = new Produtividade(produtividade, quantidade);
		
		produtividades.add(p);
	}
	
	
	private boolean setCategoria(String categoria) throws ValidacaoException {
		
		for(CategoriaPED categ: CategoriaPED.values()) {
			if(categoria.equalsIgnoreCase(categ.getCategoria())) {
				this.categoria = categ;
				return true;
			}
		}
		
		throw new ValidacaoException("Categoria invalida");
	}
	
	public String getCategoria() {
		return categoria.getCategoria();
	}

	@Override
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) throws CadastroException {
		
		if(verificaCategoria(CategoriaPED.COOPERACAO_EMPRESAS)) { // TIPO COOPERACAO COM EMPRESAS
			if(super.ehProfessor(participacaoASerAdicionada)) {  // inicio de adicao de professores COOPERACAO
				Professor prof = (Professor) participacaoASerAdicionada;
				if(prof.getCoordenador()) { // verifica se professor eh coordenador
					if(this.temProfessorCoordenador == false) { // verifica se o projeto ja contem um coordenador
						super.participacoes.add(participacaoASerAdicionada); // adiciona professor coordenador
						this.temProfessorCoordenador = true;
					} else {
						throw new CadastroException("Projetos P&D nao podem ter mais de um coordenador");
					}
				
				} else {
					if(this.temProfessorCoordenador) { // verifica se ja tem um coordenador cadastrado, para cadastrar o professor
						if(super.participacoes.contains(participacaoASerAdicionada)) {
							throw new CadastroException("Professor ja esta cadastrado nesse projeto");
						}
						super.participacoes.add(participacaoASerAdicionada); // adiciona professor normal
						
					} else {
						throw new CadastroException("Projetos P&D precisam de um coordenador");

					} // fim de adicao de professores cooperacao
			
				} 					
			} else {
				if(super.participacoes.contains(participacaoASerAdicionada)) {
					throw new CadastroException("Pessoa ja esta cadastrada nesse projeto");
				}
				super.participacoes.add(participacaoASerAdicionada); // adicao de profissionais e alunos
			}
			
		} else {
			
			if(super.ehProfessor(participacaoASerAdicionada)) {
				if(temProfessor) {
					throw new CadastroException("Projetos P&D nao podem ter mais de um professor");
				} else {
					super.participacoes.add(participacaoASerAdicionada);
					this.temProfessor = true;
				}
				
			} else if(participacaoASerAdicionada instanceof AlunoGraduando) {
				if(temAluno) {
					throw new CadastroException("Projetos P&D nao podem ter mais de um graduando");
				} else {
					super.participacoes.add(participacaoASerAdicionada);
					this.temAluno = true;
				}
				
			}
			
		}
					
	}

	private boolean verificaCategoria(CategoriaPED categoria) {
		if(categoria == this.categoria) {
			return true;
		}
		
		return false;
	}
	
	
	//public int getProdutividade(String descricao) {
		//return produtividades.getProdutividade(descricao);
	//}
	
	

}
