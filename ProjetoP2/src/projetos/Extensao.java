package projetos;

public class Extensao extends Projeto {
	
	private int impactoSocial;

	public Extensao(String nomeProjeto, String objetivoDoProjeto, int impacto, int duracao, String dataInicio) {
		super(nomeProjeto, objetivoDoProjeto, dataInicio, duracao);
		this.setImpacto(impacto);
		
	}

	public int getImpacto() {
		return impactoSocial;
	}

	public void setImpacto(int impacto) {
		this.impactoSocial = impacto;
	}

}
