package participacao;

import java.io.Serializable;

/**
 * O enum representa os tipos de vinculo de um aluno de pos-graduacao
 * @author Yuri Silva
 * @author Tiberio Gadelha
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
