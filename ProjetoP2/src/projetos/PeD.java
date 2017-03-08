package projetos;

public class PeD extends Projeto {
	
	private CategoriaPED categoria;
	

	public PeD(String nomeDoProjeto, String categoria, int prodTecnica, int prodAcademica, int patentes, String objetivoDoProjeto, String dataInicio, int duracao) {
		super(nomeDoProjeto, objetivoDoProjeto, dataInicio, duracao);
		setCategoria(categoria);
		
	}
	
	public void setCategoria(String categoria) {
		if(categoria.equalsIgnoreCase("pibit")) {
			this.categoria = CategoriaPED.PIBITI;
		} else if(categoria.equalsIgnoreCase("pibic")) {
			this.categoria = CategoriaPED.PIBIC;
		} else if(categoria.equalsIgnoreCase("pivic")) {
			this.categoria = CategoriaPED.PIVIC;
		} else if(categoria.equalsIgnoreCase("pibiti")) {
			this.categoria = CategoriaPED.PIBITI;
		} else if(categoria.equals("coop")) {
			this.categoria = CategoriaPED.CooperacaoComEmpresas;
			
		}
	}

}
