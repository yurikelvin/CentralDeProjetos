package associacao;

public enum Cargo {

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
