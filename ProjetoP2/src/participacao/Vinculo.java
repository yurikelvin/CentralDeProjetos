package participacao;

public enum Vinculo {

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
