package participacao;

import exception.ValidacaoException;

/**
 * 
 * @author Yuri Silva
 *
 */
public class Profissional extends Participacao {
	
	private Cargo cargo;
	
	/**
	 * 
	 * @param cargo
	 * @param valorHora
	 * @param qtdHoras
	 */

	public Profissional(String cargo, double valorHora, int qtdHoras) {
		super(valorHora, qtdHoras);
		this.setCargo(cargo);
	
	}
	
	/**
	 * 
	 * @param cargo
	 * @throws ValidacaoException
	 */

	private boolean setCargo(String cargo) throws ValidacaoException {
		for(Cargo valorCargo: Cargo.values()) {
			if(cargo.equalsIgnoreCase(valorCargo.getCargo())) {
				this.cargo = valorCargo;
				return true;
			}
		}
		throw new ValidacaoException("Cargo nao cadastrado");
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getCargo() {
		return cargo.getCargo();
	}

}
