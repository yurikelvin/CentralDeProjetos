package cdp.projetos;

import java.io.Serializable;

/**
 * Representa as categorias de Projetos Pesquisa e Desenvolvimento.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public enum CategoriaPED implements Serializable {
	
	 PIBIC("pibic"), 
	 PIBITI("pibiti"), 
	 PIVIC("pivic"),
	 COOPERACAO_EMPRESAS("coop");
	
	private final String categoria;
	
	CategoriaPED(String categoria) {
		this.categoria = categoria;
	}
	
	public String getCategoria() {
		return categoria;
	}
}
