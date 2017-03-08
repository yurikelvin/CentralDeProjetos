package projetos;

public enum ImpactoSocial {
	
	comunidadeAcademica(1),
	cidade(2),
	regiaoEstado(3),
	estado(4),
	regiaoFederacao(5),
	federacao(6);
	
	private int impactoSocial;
	
	ImpactoSocial(int impactoSocial) {
		this.impactoSocial = impactoSocial;
	}
	
	public int getImpactoSocial() {
		return impactoSocial;
	}
	

}
