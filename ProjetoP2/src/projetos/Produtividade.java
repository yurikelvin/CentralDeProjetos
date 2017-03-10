package projetos;

import java.io.Serializable;

public class Produtividade implements Serializable{
	
	private static final String PROD_ACADEMICA = "producao academica";
	private static final String PROD_TECNICA = "producao tecnica";
	private static final String PATENTES = "patentes";

	private String produtividade;
	private int quantidade;
	
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
