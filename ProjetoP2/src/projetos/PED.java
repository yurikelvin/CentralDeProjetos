package projetos;

import exception.ValidacaoException;

public class PED extends Projeto {
	
	private CategoriaPED categoria;
	private Produtividade produtividades;
	
	

	public PED(String nomeDoProjeto, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivoDoProjeto, String dataInicio, int duracao, int codigo)  throws ValidacaoException {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao, codigo);
		setCategoria(categoria);
		
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
	
	public int getProdutividade(String descricao) {
		return produtividades.getProdutividade(descricao);
	}
	
	

}
