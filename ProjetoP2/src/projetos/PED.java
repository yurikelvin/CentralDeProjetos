package projetos;

import java.util.HashSet;

import associacao.AlunoGraduando;
import associacao.Professor;
import exception.ValidacaoException;

public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private HashSet<Produtividade> produtividades;
	
	private AlunoGraduando alunoAssociado;
	private Professor professorOrientador;
	
	

	public PED(String nomeDoProjeto, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		setCategoria(categoria);
		this.produtividades = new HashSet<>();
		
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
	
	//public int getProdutividade(String descricao) {
		//return produtividades.getProdutividade(descricao);
	//}
	
	

}
