package participacao;

import java.io.Serializable;

/**
 * 
 * @author Yuri Silva
 *
 */

public enum Vinculo implements Serializable{

	MESTRADO("mestrado"),
	DOUTORADO("doutorado");
	
	public final String vinculo;
	
	Vinculo(String vinculo) {
		this.vinculo = vinculo;
	}
	
	public String getVinculo() {
		return vinculo;
	}
}
