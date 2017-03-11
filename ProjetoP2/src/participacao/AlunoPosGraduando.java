package participacao;

import exception.ValidacaoException;

/**
 * 
 * @author Yuri Silva
 *
 */

public class AlunoPosGraduando extends Participacao {
	
	private Vinculo vinculo;
	
	/**
	 * 
	 * @param valorHora
	 * @param qtdHoras
	 * @param vinculo
	 */

	public AlunoPosGraduando(double valorHora, int qtdHoras, String vinculo) {
		super(valorHora, qtdHoras);
		this.setVinculo(vinculo);
	}
	
	/**
	 * 
	 * @param vinculo
	 * @throws ValidacaoException
	 */
	
	
	private boolean setVinculo(String vinculo) throws ValidacaoException {
		for(Vinculo valorVinculo: Vinculo.values()) {
			if(vinculo.equalsIgnoreCase(valorVinculo.getVinculo())) {
				this.vinculo = valorVinculo;
				return true;
			}
		}
		
		throw new ValidacaoException("Vinculo nao cadastrado");
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getVinculo() {
		return vinculo.getVinculo();
	}

}
