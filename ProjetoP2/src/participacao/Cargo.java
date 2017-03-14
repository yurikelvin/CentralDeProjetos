package participacao;

import java.io.Serializable;

/**
 * O enum representa os tipos de cargo de um Profissional.
 * @author Yuri Silva
 * @author Tiberio Gadelha
 */

public enum Cargo implements Serializable {

	DESENVOLVEDOR("desenvolvedor"),
	GERENTE("gerente"),
	PESQUISADOR("pesquisador");
	
	private final String cargo;
	
	Cargo(String cargo) {
		this.cargo = cargo;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
	
}
