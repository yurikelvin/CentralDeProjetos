package cdp.projetos;

import java.io.Serializable;

/**
 * Representa os tipos de impactos sociais que um projeto pode abranger.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 */

public enum ImpactoSocial  implements Serializable{
	
	COMUNIDADE_ACADEMICA(1, "Comunidade Academica"),
	CIDADE(2, "Cidade"),
	REGIAO_ESTADO(3, "Regional (Dentro do Estado)"),
	ESTADO(4, "Estado"),
	REGIAO_FEDERACAO(5, "Regiao (Dentro da Federacao/Brasil)"),
	FEDERACAO(6, "Federacao (Brasil)");
	
	private String impacto;
	private int valorImpactoSocial;
	
	ImpactoSocial(int valorImpactoSocial, String impacto) {
		this.valorImpactoSocial = valorImpactoSocial;
		this.impacto = impacto;
	}
	
	public int getValorImpactoSocial() {
		return valorImpactoSocial;
	}
	
	public String getImpactoSocial() {
		return impacto;
	}
	

}
