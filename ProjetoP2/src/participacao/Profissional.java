package participacao;

import exception.ValidacaoException;

public class Profissional extends Participacao {
	
	private Cargo cargo;

	public Profissional(String cargo, double valorHora, int qtdHoras) {
		super(valorHora, qtdHoras);
		this.setCargo(cargo);
	
	}

	private void setCargo(String cargo) throws ValidacaoException {
		for(Cargo valorCargo: Cargo.values()) {
			if(cargo.equalsIgnoreCase(valorCargo.getCargo())) {
				this.cargo = valorCargo;
			}
		}
		throw new ValidacaoException("Cargo nao cadastrado");
	}
	
	public String getCargo() {
		return cargo.getCargo();
	}

}
