package projetos;

import java.util.HashSet;

import exception.ValidacaoException;
import participacao.AlunoGraduando;
import participacao.Participacao;
import participacao.Professor;

public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private HashSet<Produtividade> produtividades;
	
	private AlunoGraduando alunoAssociado;

	
	boolean temProfessorCoordenador;
	boolean temProfessor;
	boolean temAluno;
	
	

	public PED(String nomeDoProjeto, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		setCategoria(categoria);
		this.produtividades = new HashSet<>();
		
		this.temProfessorCoordenador = false;
		this.temProfessor = false;
		this.temAluno = false;
		
	}
	
	public String getProdutividades() {
		return produtividades.toString();
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
	public void adicionaParticipacao(Participacao participacaoASerAdicionada) {
		if(participacaoASerAdicionada instanceof Professor) {
			Professor prof = (Professor) participacaoASerAdicionada;
			if(verificaCategoria(CategoriaPED.COOPERACAO_EMPRESAS)) { // inicio de adicao de professores COOPERACAO

				if(temProfessorCoordenador == false) {
					
					if(prof.getCoordenador()) {
						participacoes.add(participacaoASerAdicionada);
						temProfessorCoordenador = true;
					} else {
						throw new ValidacaoException("Projeto P&D COOPERACAO precisa de 1 professor coordenador");
					}
					
				} else {
					
					if(prof.getCoordenador()) {
						throw new ValidacaoException("Projetos P&D nao podem ter mais de um coordenador");
					}
					
					participacoes.add(participacaoASerAdicionada);
					
					
				}
				
			}  // fim de adicao de professores cooperacao
			else { // inicio de adicao de professor PIBIC, PIBITI, etc..
				
				if(temProfessor) {
					throw new ValidacaoException("Projetos P&D nao podem ter mais de um professor");
				}
				
				participacoes.add(participacaoASerAdicionada);
				temProfessor = true;
				
			} // fim de adicao de professor PIBIC,PIBITI, etc ....
		} else if (participacaoASerAdicionada instanceof AlunoGraduando) // adicao de 1 aluno graduando PIBIC,PIBITI,etc...
		{
			
			
			
		}
		
	}

	@Override
	public void removeParticipacao(Participacao participacaoASerRemovida) {
	
		
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
