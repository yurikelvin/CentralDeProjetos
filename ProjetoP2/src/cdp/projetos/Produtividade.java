package cdp.projetos;

import java.io.Serializable;

/**
 * Representa uma produtividade que o projeto pode ter.
 * 
 * @author Yuri Silva
 * @author Tiberio Gadelha
 * @author Matheus Henrique
 * @author Gustavo Victor
 *
 */

public class Produtividade implements Serializable{
	
	private static final String PROD_ACADEMICA = "producao academica";
	private static final String PROD_TECNICA = "producao tecnica";
	private static final String PATENTES = "patentes";

	private String produtividade;
	private int quantidade;
	
	/**
	 * Cria uma produtividade com sua respectiva quantidade.
	 * Produtividades aceitas: producao academica, producao tecnica e patentes.
	 * @param produtividade Produtividade a ser adicionada.
	 * @param quantidade Quantidade da produtividade.
	 */
	
	public Produtividade(String produtividade, int quantidade) {
		this.setProdutividade(produtividade, quantidade);
		
	}
	
	public String getProdutividade() {
		return produtividade;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	private void setProdutividade(String produtividade, int quantidade) {
		
		switch(produtividade.toLowerCase()) {
			case PROD_ACADEMICA:
				this.produtividade = produtividade;
				this.quantidade = quantidade;
				break;
			case PROD_TECNICA:
				this.produtividade = produtividade;
				this.quantidade = quantidade;
				break;
			case PATENTES:
				this.produtividade = produtividade;
				this.quantidade = quantidade;
				break;
			default:
				break;
		}
	}
	
	
	

}
