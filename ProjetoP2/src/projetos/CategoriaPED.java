package projetos;

import java.io.Serializable;

/**
 * 
 * @author tsubakker
 *
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
