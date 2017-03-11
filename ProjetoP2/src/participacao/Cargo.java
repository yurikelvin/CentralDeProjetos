package participacao;

import java.io.Serializable;

/**
 * 
 * @author Yuri Silva
 *
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
